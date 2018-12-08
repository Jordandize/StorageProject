import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCardOpComponent } from './product-card.component';

describe('ProductCardComponent', () => {
  let component: ProductCardOpComponent;
  let fixture: ComponentFixture<ProductCardOpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductCardOpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCardOpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
