import {Component, OnInit} from '@angular/core';
import {AdvertisingDto, AdvertisingService} from "../generated";
import {ActivatedRoute, Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {PopUpDialogComponent} from "../pop-up-dialog/pop-up-dialog.component";

@Component({
  selector: 'app-advertising-favourites',
  templateUrl: './advertising-favorites.component.html',
  styleUrls: ['./advertising-favorites.component.scss']
})
export class AdvertisingFavoritesComponent implements OnInit {
  constructor(private advertisingService: AdvertisingService,
              private route: ActivatedRoute,
              private router: Router,
              public dialog: MatDialog,) {
  }

  displayedColumns: string[] = ['id', 'text', 'email', 'detail', 'activation', 'delete', 'favorite'];
  advertisings: AdvertisingDto[] = [];
  activeAdvertisings: MatTableDataSource<AdvertisingDto>;
  inactiveAdvertisings: MatTableDataSource<AdvertisingDto>;

  getAds(): void {
    this.advertisingService.getFavoriteAds().subscribe(
      (ad: AdvertisingDto[]) => {
        let actives: AdvertisingDto[] = []
        let inactives: AdvertisingDto[] = []
        this.advertisings = ad;
        this.advertisings.forEach((selectedAd: AdvertisingDto) => {
          if (selectedAd.isActive) {
            actives.push(selectedAd);
            console.log('active:', selectedAd)
          } else {
            inactives.push(selectedAd);
            console.log('inactive:', selectedAd)
          }
        });
        this.activeAdvertisings = new MatTableDataSource(actives);
        this.inactiveAdvertisings = new MatTableDataSource(inactives);
      });
  }

  detail(id: string): void {
    this.router.navigate(['advertising', id]);
  }

  delete(id: number): void {
    this.advertisingService.deleteAdvertising(id).subscribe(
      (data: AdvertisingDto) => {
        console.log(data);
        this.getAds();
      }
    );
  }

  setStatus(id: number): void {
    this.advertisingService.setAdStatus(id).subscribe(
      (data: any) => {
        console.log(data);
        this.getAds();
      }
    );
  }

  ngOnInit(): void {
    this.getAds();
  }

  setFavoriteStatus(id: number): void {
    this.advertisingService.setAdFavoriteStatus(id).subscribe(
      (data: any) => {
        console.log(data);
        this.getAds();
      }
    );
  }

  openDialog(id: number) {
    let dialogRef = this.dialog.open(PopUpDialogComponent, {
      data: false
    })

    dialogRef.afterClosed().subscribe(res => {
      if (res.data === true) {
        console.log(res.data);
        this.delete(id);
      }
    })
  }
}
