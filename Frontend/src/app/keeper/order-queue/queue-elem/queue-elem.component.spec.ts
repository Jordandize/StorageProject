import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QueueElemComponent } from './queue-elem.component';

describe('QueueElemComponent', () => {
  let component: QueueElemComponent;
  let fixture: ComponentFixture<QueueElemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QueueElemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QueueElemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
