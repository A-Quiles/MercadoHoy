import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CryptoDto {
  id: string;
  symbol: string;
  name: string;
  priceUsd: number;
  changePercent24Hr: number;
}

@Injectable({ providedIn: 'root' })
export class CriptoService {

  private apiUrl = 'http://localhost:8080/api/crypto';

  constructor(private http: HttpClient) {}

  getCrypto(symbol: string): Observable<CryptoDto> {
    return this.http.get<CryptoDto>(`${this.apiUrl}/asset/${symbol}`);
  }

  getCryptos(symbols: string[]): Observable<CryptoDto[]> {
    const param = symbols.join(',');
    return this.http.get<CryptoDto[]>(`${this.apiUrl}/assets?symbols=${param}`);
  }
}
