import { Component } from '@angular/core';
import { BotonInicioComponent } from '../../componentes/boton-inicio/boton-inicio.component';

@Component({
  selector: 'app-nosotros',
  standalone: true,
  imports: [BotonInicioComponent],
  templateUrl: './nosotros.component.html',
  styleUrls: ['./nosotros.component.css'],
})
export class NosotrosComponent {}
