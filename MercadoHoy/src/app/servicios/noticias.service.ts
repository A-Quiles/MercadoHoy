import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, shareReplay } from 'rxjs/operators';
import { Observable } from 'rxjs';

export interface Noticia {
  id: string;
  titulo: string;
  texto: string;
  fuente?: string;
  url?: string;
  fechaISO: string;
}

@Injectable({ providedIn: 'root' })
export class NoticiasService {
  constructor(private http: HttpClient) {}

  listar(limit = 50): Observable<Noticia[]> {
    // Lee el JSON estático desde /assets/noticias.json
    return this.http.get<Noticia[]>('/assets/noticias.json')
      .pipe(
        map(arr => (arr || []).sort((a, b) => b.fechaISO.localeCompare(a.fechaISO)).slice(0, limit)),
        shareReplay(1)
      );
  }
}
