import {
  Component,
  OnInit,
  OnDestroy,
  inject,
  PLATFORM_ID
} from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CriptoService, CryptoDto } from '../../servicios/cripto.service';

@Component({
  selector: 'app-cripto-panel',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cripto-panel.component.html',
  styleUrls: ['./cripto-panel.component.css']
})
export class CriptoPanelComponent implements OnInit, OnDestroy {

  // 10 criptos principales
  private readonly defaultSymbols: string[] = [
    'BTC', 'ETH', 'BNB', 'SOL', 'XRP',
    'ADA', 'DOGE', 'TON', 'AVAX', 'LTC'
  ];

  cryptos: CryptoDto[] = [];
  errorMsg: string = '';

  private refreshId: any;
  private readonly REFRESH_MS = 5000;
  private platformId = inject(PLATFORM_ID);

  // === Ordenación ===
  sortColumn: keyof CryptoDto = 'symbol';
  sortDirection: 'asc' | 'desc' = 'asc';

  constructor(private criptoService: CriptoService) {}

  ngOnInit(): void {
    this.loadAllCryptos();

    // Auto-refresh SOLO en navegador (no en SSR)
    if (isPlatformBrowser(this.platformId)) {
      this.refreshId = setInterval(() => {
        this.loadAllCryptos();
      }, this.REFRESH_MS);
    }
  }

  ngOnDestroy(): void {
    if (this.refreshId && isPlatformBrowser(this.platformId)) {
      clearInterval(this.refreshId);
    }
  }

  // Cargar o refrescar todas las criptos
  private loadAllCryptos(): void {
    this.criptoService.getCryptos(this.defaultSymbols).subscribe({
      next: (data) => {
        if (this.cryptos.length === 0) {
          // Primera vez: cargamos y aplicamos el orden inicial
          this.cryptos = [...data];
          this.applySorting();
        } else {
          // Siguientes veces: solo actualizamos valores, manteniendo el orden actual
          const map = new Map<string, CryptoDto>(
            data.map(d => [d.symbol, d])
          );

          this.cryptos = this.cryptos.map(oldRow => {
            const updated = map.get(oldRow.symbol);
            return updated ? { ...oldRow, ...updated } : oldRow;
          });
        }
      },
      error: (err) => {
        console.error('Error cargando criptos', err);
        this.errorMsg = 'No se pudieron cargar los datos del mercado.';
      }
    });
  }

  // === ORDENACIÓN ===
  sortBy(column: keyof CryptoDto) {
    if (this.sortColumn === column) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortColumn = column;
      this.sortDirection = 'asc';
    }
    this.applySorting();
  }

  applySorting() {
    const key = this.sortColumn;

    this.cryptos = [...this.cryptos].sort((a, b) => {
      let valA: any = a[key];
      let valB: any = b[key];

      if (typeof valA === 'string' && !isNaN(Number(valA))) valA = Number(valA);
      if (typeof valB === 'string' && !isNaN(Number(valB))) valB = Number(valB);

      if (valA < valB) return this.sortDirection === 'asc' ? -1 : 1;
      if (valA > valB) return this.sortDirection === 'asc' ? 1 : -1;
      return 0;
    });
  }
}
