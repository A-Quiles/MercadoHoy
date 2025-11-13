import { Component } from '@angular/core';
import { BotonInicioComponent } from '../../componentes/boton-inicio/boton-inicio.component';

@Component({
  selector: 'app-legales',
  standalone: true,
  imports: [BotonInicioComponent],
  templateUrl: './legales.component.html',
  styleUrls: ['./legales.component.css'],
})
export class LegalesComponent {}
