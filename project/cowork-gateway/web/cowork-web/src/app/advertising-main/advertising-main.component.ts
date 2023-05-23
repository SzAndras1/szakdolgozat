import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {AdvertisingDto, AdvertisingPageResultDto, AdvertisingService} from "../generated";
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import {MatPaginator} from "@angular/material/paginator";
import {Router} from '@angular/router';
import {MatDialog} from "@angular/material/dialog";
import {PopUpDialogComponent} from "../pop-up-dialog/pop-up-dialog.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-adwertising-test',
  templateUrl: './advertising-main.component.html',
  styleUrls: ['./advertising-main.component.scss']
})
export class AdvertisingMainComponent implements AfterViewInit, OnInit {

  displayedColumns: string[] = ['id', 'userId', 'text', 'email', 'detail', 'activation', 'delete', 'favorite'];
  database: AdvertisingHttpDatabase | null;
  filteredAndPagedIssues: Observable<AdvertisingDto[]>;

  resultsLength: number = 0;
  isLoadingResults: boolean = true;
  isRateLimitReached: boolean = false;
  successfulCreate: boolean = false;
  snackBarDurationInSeconds: number = 3;

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
    },
    userId: {
      name: 'userId',
      columnProp: 'userId',
      options: [],
      modelValue: undefined
    }
  };

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private advertisingService: AdvertisingService,
    private router: Router,
    public dialog: MatDialog,
    private _snackBar: MatSnackBar
  ) {
    this.successfulCreate = this.router.getCurrentNavigation()?.extras.state?.['successfulCreate'];
  }

  ngAfterViewInit(): void {
    this.loadTableData();
  }

  loadTableData(): void {
    setTimeout(() => {
      this.database = new AdvertisingHttpDatabase(this.advertisingService);

      this.filteredAndPagedIssues = merge(this.paginator.page)
        .pipe(
          startWith({}),
          switchMap(() => {
            this.isLoadingResults = true;
            this.filterValues["isActive"] = true;
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

  filterChange(filter: any, event: any, type: string) {
    if (type == 'string')
      this.filterValues[filter.columnProp] = event.target.value;
    if(type == 'number' && !isNaN(event.target.valueAsNumber)) {
      this.filterValues[filter.columnProp] = event.target.valueAsNumber
    }
    this.paginator.pageIndex = 0;
    this.loadTableData();
  }

  resetFilters(): void {
    this.filterValues = {};
    Object.keys(this.filterSelectObj).map((key: string) => this.filterSelectObj[key as keyof typeof this.filterSelectObj].modelValue = undefined);
    this.paginator.pageIndex = 0;
    this.loadTableData();
  }

  add(): void {
    this.router.navigate(['advertising/add']);
  }

  detail(id: string): void {
    this.router.navigate(['advertising', id]);
  }

  navigateToUserProfile(userId: string) {
    this.router.navigate(['advertising/user', userId]);
  }

  delete(id: number): void {
    this.advertisingService.deleteAdvertising(id).subscribe(
      (data: AdvertisingDto) => {
        console.log(data);
      }
    );
    this.loadTableData();
  }

  openDialog(id: number): void {
    let dialogRef = this.dialog.open(PopUpDialogComponent, {
      data: false
    });

    dialogRef.afterClosed().subscribe(res => {
      if (res.data === true) {
        console.log(res.data);
        this.delete(id);
      }
    });
  }

  setStatus(id: number): void {
    this.advertisingService.setAdStatus(id).subscribe(
      (data: any) => {
        this.loadTableData();
        console.log(data);
      }
    );
  }

  setFavoriteStatus(id: number): void {
    this.advertisingService.setAdFavoriteStatus(id).subscribe(
      (data: any) => {
        this.loadTableData();
        console.log(data);
      }
    );
  }

  openSnackBar(message: string, action: string): void {
    this._snackBar.open(message, action, {duration: this.snackBarDurationInSeconds * 1000, verticalPosition: 'top'},);
  }

  ngOnInit(): void {
    this.loadTableData();
    if(this.successfulCreate){
      this.openSnackBar("Successful Create!", "Close");
    }
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
