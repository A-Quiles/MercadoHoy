import { Component, OnInit, OnDestroy, inject, PLATFORM_ID } from '@angular/core';
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

  // Las 10 criptos que quieres mostrar
  private readonly defaultSymbols: string[] = [
    'BTC', 'ETH', 'BNB', 'SOL', 'XRP',
    'ADA', 'DOGE', 'TON', 'AVAX', 'LTC'
  ];

  cryptos: CryptoDto[] = [];
  errorMsg: string = '';

  private refreshId: any;
  private readonly REFRESH_MS = 5000; // 5 segundos

  // Para saber si estamos en navegador o en servidor (SSR)
  private platformId = inject(PLATFORM_ID);

  constructor(private criptoService: CriptoService) {}

  ngOnInit(): void {
    // 1) Carga inicial: se puede hacer tanto en servidor como en navegador
    this.loadAllCryptos();

    // 2) Refresco automático SOLO en navegador (no en SSR)
    if (isPlatformBrowser(this.platformId)) {
      this.refreshId = setInterval(() => {
        this.loadAllCryptos();
      }, this.REFRESH_MS);
    }
  }

  ngOnDestroy(): void {
    if (isPlatformBrowser(this.platformId) && this.refreshId) {
      clearInterval(this.refreshId);
    }
  }

  // Carga o recarga todas las criptos
  private loadAllCryptos(): void {
    this.errorMsg = '';

    this.criptoService.getCryptos(this.defaultSymbols).subscribe({
      next: (data) => {
        // Aquí Angular SOLO actualiza los datos, no recarga el componente
        this.cryptos = data;
      },
      error: (err) => {
        console.error('Error cargando criptos', err);
        this.errorMsg = 'No se pudieron cargar los datos de mercado.';
      }
    });
  }
}
