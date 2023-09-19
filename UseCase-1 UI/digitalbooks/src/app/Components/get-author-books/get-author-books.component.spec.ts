import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetAuthorBooksComponent } from './get-author-books.component';

describe('GetAuthorBooksComponent', () => {
  let component: GetAuthorBooksComponent;
  let fixture: ComponentFixture<GetAuthorBooksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetAuthorBooksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetAuthorBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
