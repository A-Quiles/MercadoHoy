import { Component } from '@angular/core';
import { BotonInicioComponent } from '../../componentes/boton-inicio/boton-inicio.component';

@Component({
  selector: 'app-cripto',
  standalone: true,
  imports: [BotonInicioComponent],
  templateUrl: './cripto.component.html',
  styleUrls: ['./cripto.component.css'],
})
export class CriptoComponent {}
