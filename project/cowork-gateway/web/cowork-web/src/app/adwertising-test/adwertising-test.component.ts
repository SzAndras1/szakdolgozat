import {AfterViewInit, Component, ViewChild} from "@angular/core";
import {AdvertisingDto, AdvertisingPageResultDto, AdvertisingService} from "../generated";
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import {MatPaginator} from "@angular/material/paginator";
import {Router} from '@angular/router';

@Component({
  selector: 'app-adwertising-test',
  templateUrl: './adwertising-test.component.html',
  styleUrls: ['./adwertising-test.component.scss']
})
export class AdwertisingTestComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'userId', 'text', 'email', 'detail', 'activation' ,'delete'];
  database: AdvertisingHttpDatabase | null;
  filteredAndPagedIssues: Observable<AdvertisingDto[]>;

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  filterValues: any = {};
  filterSelectObj = {
    text: {
      name: 'text',
      columnProp: 'text',
      options: [],
      modelValue: undefined
    },
    email: {
      name: 'email',
      columnProp: 'email',
      options: [],
      modelValue: undefined
    }
  };

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private advertisingService: AdvertisingService,
    private router: Router
  ) {
  }

  ngAfterViewInit() {
    this.loadTableData();
  }

  loadTableData() {
    setTimeout(() => {
      this.database = new AdvertisingHttpDatabase(this.advertisingService);

      this.filteredAndPagedIssues = merge(this.paginator.page)
        .pipe(
          startWith({}),
          switchMap(() => {
            this.isLoadingResults = true;
            return this.database!.searchAdvertising(
              this.paginator.pageIndex, this.paginator.pageSize, this.filterValues);
          }),
          map((data: any) => {
            // Flip flag to show that loading has finished.
            this.isLoadingResults = false;
            this.isRateLimitReached = false;
            this.resultsLength = data.totalElements;

            return data.content;
          }),
          catchError(() => {
            this.isLoadingResults = false;
            // Catch if the GitHub API has reached its rate limit. Return empty data.
            this.isRateLimitReached = true;
            return observableOf([]);
          })
        );
    });
  }

  resetPaging(): void {
    this.paginator.pageIndex = 0;
    this.resetFilters();
    this.loadTableData();
  }

  filterChange(filter: any, event: any) {
    this.filterValues[filter.columnProp] = event.target.value;
    this.paginator.pageIndex = 0;
    this.loadTableData();
  }

  resetFilters() {
    this.filterValues = {};
    this.paginator.pageIndex = 0;
    this.loadTableData();
  }

  add() {
    this.router.navigate(['advertising/add']);
  }

  detail(id: string) {
    this.router.navigate(['advertising',id]);
  }

  navigateToUserProfile(userId : string){
    this.router.navigate(['advertising/user',userId]);
  }

  delete(id: number) {
    this.advertisingService.deleteAdvertising(id).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }
  setStatus(id: number) {
    this.advertisingService.setAdStatus(id).subscribe(
      (data: any) => {
        console.log(data);
      }
    );
  }
}

export class AdvertisingHttpDatabase {
  constructor(private advertisingService: AdvertisingService) {
  }

  searchAdvertising(page: number, size: number, filterValues: any): Observable<AdvertisingPageResultDto> {
    const filters: any[] = [];
    Object.keys(filterValues).forEach(key => filters.push({
      field: key,
      filter: filterValues[key]
    }));
    return this.advertisingService.searchAdvertising({page: page, size: size, filters: filters});
  }
}
