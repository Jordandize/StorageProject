import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionViewerComponent } from './session-viewer.component';

describe('SessionViewerComponent', () => {
  let component: SessionViewerComponent;
  let fixture: ComponentFixture<SessionViewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SessionViewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SessionViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
