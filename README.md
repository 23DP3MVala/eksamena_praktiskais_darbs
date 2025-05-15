# Piezīmju pārvaldnieks
Konsoles lietotne, kurā ir iespējams izveidot, skatīt un dzēst piezīmes. Lai būtu vieglāk pārskatīt izveidotās piezīmes, tās arī ir iespējams filtrēt un kārtot. 
***
## Interfeiss
Lietotāja interfeiss ir latviešu valodā. Opciju izvēlei tiek izmantoti cipari. Programmas galvenajā logā tiek dotas četras galvenās opcijas:
- `1. Pievienot piezīmi` - ļauj lietotājam izveidot jaunu piezīmi, uzrakstot tās virsrakstu un saturu,
- `2. Apskatīt piezīmes` - tabulas veidā izvada visas saglabātās piezīmes ar to attiecīgo numuru, virsrakstu un izveidošanas laiku. Ļauj atvērt kādu piezīmi atsevišķi izvēloties tās attiecīgo numuru, kā arī filtrēt un kārtot piezīmes,
- `3. Dzēst piezīmi` - ļauj lietotājam izdzēst kādu no izveidotajām piezīmēm ievadot piezīmes attiecīgo numuru,
- `4. Iziet` - pārtrauc programmas darbību.
***
## Funkcijas
Programmas pirmreizējās palaišanas laikā tiek izveidots tukšs .json fails, kurā tiek saglabātas piezīmes (`notes.json`).  
Lai atvieglotu piezīmju pārskatīšanu, ir pieejama piezīmju filtrēšana pēc datuma un piezīmju kārtošana pēc datuma vai nosaukuma.
Lai piekļūtu filtrēšanai vai kārtošanai:
1. Galvenajā logā izvēlās `2. Apskatīt piezīmes`,
2. Kā opciju ievada 0.
### Filtrēšana
1. Kā opciju ievada 1 lai filtrētu pēc datuma,
2. Ievada sākuma datumu `dd-MM-yyyy` formātā,
3. Ievada beigu datumu `dd-MM-yyyy` formātā.
Filtram atbilstošās pizīmes tiek izvadītas tabulas veidā un ļauj lietotājam atvērt kādu no piezīmēm ievadot tās attiecīgo numuru vai atgriezties galvenajā logā ievadot 0.
### Kārtošana
1. Kā opciju ievada 2 lai kārtotu,
2. Izvēlās pēc kā kārtot: 1 - sakārtot pēc datuma dilstošā secībā (noklusējums), 2 - sakārtot pēc datuma augošā secībā, 3 - sakārtot pēc nosaukuma alfabēta secībā, 4 - sakārtot pēc nosaukuma dilstošā alfabēta secībā.
Sakārtotās piezīmes tiek izvadītas tabulas veidā un ļauj lietotājam atvērt kādu no piezīmēm ievadot tās attiecīgo numuru vai atgriezties galvenajā logā ievadot 0.
