import { Component } from '@angular/core';
import { AsyncPipe, DatePipe, NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';
import { NoticiasService, Noticia } from '../../servicios/noticias.service';
import { Observable } from 'rxjs';

// IMPORTAR EL COMPONENTE DEL PANEL CRIPTO
import { CriptoPanelComponent } from '../../componentes/cripto-panel/cripto-panel.component';

@Component({
  selector: 'app-inicio',
  standalone: true,
  // AÑADIDO CriptoPanelComponent A LOS IMPORTS
  imports: [
    NgIf,
    NgFor,
    AsyncPipe,
    DatePipe,
    RouterLink,
    CriptoPanelComponent,   // <-- AQUÍ
  ],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css'],
})
export class InicioComponent {

  // Noticias desde el servicio
  noticias$: Observable<Noticia[]> = this.noticiasSrv.listar(50);

  // Año actual para el footer
  year = new Date().getFullYear();

  constructor(private noticiasSrv: NoticiasService) {}
}
