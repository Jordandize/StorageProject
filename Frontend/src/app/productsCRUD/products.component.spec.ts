import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsOpComponent } from './products.component';

describe('ProductsComponent', () => {
  let component: ProductsOpComponent;
  let fixture: ComponentFixture<ProductsOpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductsOpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsOpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
