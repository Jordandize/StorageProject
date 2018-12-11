import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QueueColumnComponent } from './queue-column.component';

describe('QueueColumnComponent', () => {
  let component: QueueColumnComponent;
  let fixture: ComponentFixture<QueueColumnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QueueColumnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QueueColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
