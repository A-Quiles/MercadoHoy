import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CriptoPanelComponent } from './cripto-panel.component';

describe('CriptoPanelComponent', () => {
  let component: CriptoPanelComponent;
  let fixture: ComponentFixture<CriptoPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CriptoPanelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CriptoPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
