import {Component, Input, OnInit} from '@angular/core';
import {CommentDto, CommentService} from "../generated";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PopUpDialogComponent} from "../pop-up-dialog/pop-up-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-comment-display',
  templateUrl: './comment-display.component.html',
  styleUrls: ['./comment-display.component.scss']
})
export class CommentDisplayComponent implements OnInit {
  @Input() adId: number;
  comments: CommentDto[] = [];
  commentCreateForm: FormGroup;
  commentModifyStatus: boolean[] = [];
  commentModifyForm: FormGroup;
  responseMode = false;

  constructor(private commentService: CommentService,
              private fb: FormBuilder,
              public dialog: MatDialog) {
  }

  getComments(): void {
    this.commentService.getAnAdvertEveryComment(this.adId).subscribe(
      (comments: CommentDto[]) => this.comments = comments);
  }

  deleteComment(id: number): void {
    this.commentService.deleteComment(id).subscribe(
      (deletedComment: CommentDto) => {
        console.log(deletedComment)
        this.getComments();
      });
  }

  response(userId: number): void {
    this.responseMode = true;
    if (this.commentCreateForm.contains('receiverId')) {
      this.commentCreateForm.patchValue({
        receiverId: userId
      });
    } else {
      this.commentCreateForm.addControl('receiverId', new FormControl(userId));
    }
    window.scrollTo(0, document.body.scrollHeight);
  }

  cancelResponse(): void {
    this.responseMode = false;
    this.commentCreateForm.removeControl('receiverId');
  }

  createComment(): void {
    this.commentService.createComment(this.commentCreateForm.value as CommentDto)
      .subscribe((newComment: CommentDto) => {
        console.log(newComment);
        this.getComments();
      });
    this.commentModifyStatus.push(false);
    if (this.commentCreateForm.controls['receiverId']) {
      this.cancelResponse();
    }
    this.commentCreateForm.patchValue({
      userId: 0,
      text: ""
    });
    this.commentCreateForm.controls['text'].setErrors(null);
  }

  updateComment(index: number): void {
    this.commentService.updateComment(this.commentModifyForm.value as CommentDto)
      .subscribe((updatedComment: CommentDto) => {
        console.log(updatedComment);
        this.getComments();
      });
    this.enterModifyMode(index);
  }

  openDialog(id: number) {
    let dialogRef = this.dialog.open(PopUpDialogComponent, {
      data: false
    })

    dialogRef.afterClosed().subscribe(res => {
      if (res.data === true) {
        console.log(res.data)
        this.deleteComment(id);
      }
    })
  }

  enterModifyMode(index: number): void {
    if (!this.commentModifyStatus[index]) {
      this.commentModifyForm.patchValue({
        id: this.comments[index].id,
        userId: this.comments[index].userId,
        receiverId: this.comments[index].receiverId,
        text: this.comments[index].text
      })
    }
    this.commentModifyStatus[index] = !this.commentModifyStatus[index];
  }

  ngOnInit(): void {
    this.getComments();
    this.commentCreateForm = this.fb.group({
        userId: [0, [Validators.required, Validators.pattern('^[0-9]*$')]],
        adId: [this.adId],
        text: ['', [Validators.required, Validators.minLength(2)]]
      }
    );
    this.commentModifyForm = this.fb.group({
        id: [0],
        userId: [0, [Validators.required, Validators.pattern('^[0-9]*$')]],
        adId: [this.adId],
        receiverId: [-1],
        text: ['', [Validators.required, Validators.minLength(2)]]
      }
    );
    for (let i = 0; i < this.commentModifyStatus.length; i++) {
      this.commentModifyStatus[i] = false;
    }
  }
}
