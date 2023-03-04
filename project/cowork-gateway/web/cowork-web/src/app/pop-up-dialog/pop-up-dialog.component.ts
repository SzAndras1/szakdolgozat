import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-pop-up-dialog',
  templateUrl: './pop-up-dialog.component.html',
  styleUrls: ['./pop-up-dialog.component.scss']
})
export class PopUpDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: boolean,
              private dialogRef: MatDialogRef<PopUpDialogComponent>) { }

  cancel() {
    this.dialogRef.close({ data: false })
  }

  confirm() {
    this.dialogRef.close({ data: true })
  }
}
