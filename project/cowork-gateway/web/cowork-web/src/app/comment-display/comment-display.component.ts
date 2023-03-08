import {Component, Input} from '@angular/core';
import {CommentDto, CommentService} from "../generated";

@Component({
  selector: 'app-comment-display',
  templateUrl: './comment-display.component.html',
  styleUrls: ['./comment-display.component.scss']
})
export class CommentDisplayComponent {
  @Input() adId: number;
  comments: CommentDto[] = [];

  constructor(private commentService: CommentService) {
  }

  getComments(): void{
    this.commentService.getAnAdvertEveryComment(this.adId).subscribe(
      (comments: CommentDto[]) => this.comments = comments
    )
  }

  ngOnInit(){
    this.getComments();
  }
}
