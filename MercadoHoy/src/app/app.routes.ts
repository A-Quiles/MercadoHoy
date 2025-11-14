import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'inicio' },
  {
    path: 'inicio',
    loadComponent: () =>
      import('./paginas/inicio/inicio.component').then(m => m.InicioComponent),
  },
  {
    path: 'legales',
    loadComponent: () =>
      import('./paginas/legales/legales.component').then(m => m.LegalesComponent),
  },
  {
    path: 'nosotros',
    loadComponent: () =>
      import('./paginas/nosotros/nosotros.component').then(m => m.NosotrosComponent),
  },
  { path: '**', redirectTo: 'inicio' },
];
