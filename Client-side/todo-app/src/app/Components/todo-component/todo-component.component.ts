import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { TodoRemove } from 'src/app/actions/todo.action';
import { Todo } from 'src/app/models/Todo';

@Component({
  selector: 'app-todo-component',
  templateUrl: './todo-component.component.html',
  styleUrls: ['./todo-component.component.css']
})
export class TodoComponentComponent implements OnInit {

  todos : Todo[];

  removeTodo(index:number) {
    this.store.dispatch(new TodoRemove(index))
  }
  constructor(private store : Store<{todos : Todo[]}>) { 
    store.pipe(select('todos')).subscribe((values)=>{
      this.todos = values;
      console.log(values);
    })
  }

  ngOnInit(): void {
  }

}
