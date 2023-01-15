import {Component, ViewChild} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {Location} from "@angular/common";
import {Router} from "@angular/router";
import {merge, Observable, of as observableOf} from "rxjs";
import {AdvertisingHttpDatabase} from "../adwertising-test/adwertising-test.component";
import {catchError, map, startWith, switchMap} from "rxjs/operators";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-advertising-list-ads',
  templateUrl: './advertising-list-ads.component.html',
  styleUrls: ['./advertising-list-ads.component.scss']
})
export class AdvertisingListAdsComponent {
  constructor(private router: Router,
              private advertisingService: AdvertisingService,
              private location: Location) {
  }

  displayedColumns: string[] = ['id', 'more'];
  database: AdvertisingHttpDatabase | null;
  everyUser: Observable<AdvertisingDto[]>;

  switchToUserAds(userId: string): void{
    this.router.navigate(['advertising/list-ads/' + userId]);
  }

  goBack(): void {
    this.location.back();
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.loadTableData();
  }

  filterValues: any = {};
  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  loadTableData() {
    setTimeout(() => {
      this.database = new AdvertisingHttpDatabase(this.advertisingService);

      this.everyUser = merge(this.paginator.page)
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
}
