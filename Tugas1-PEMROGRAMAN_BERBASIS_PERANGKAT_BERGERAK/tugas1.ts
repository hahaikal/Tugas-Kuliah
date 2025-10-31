// SOAL 1
const NIM = "050459737";
const tinggiSegitiga = 7;

function buatSegitiga(tinggi: number): void {
  console.log("--- Soal 1: Pola Segitiga NIM ---");
  console.log(`Tinggi segitiga berdasarkan NIM (digit terakhir = ${tinggi}):`);

  for (let i = 1; i <= tinggi; i++) {
    let baris = "";
    for (let j = 1; j <= i; j++) {
      baris += j;
    }
    console.log(baris);
  }
  console.log("\n");
}

//SOAL 2

const angkaAwal = 37;
const beda = 7 + 1;

function deretAritmatika(awal: number, selisih: number, jumlah: number): void {
  console.log("--- Soal 2: Deret Aritmatika NIM ---");
  console.log(`Angka Awal (2 digit terakhir NIM): ${awal}`);
  console.log(`Beda (Digit ke-3 dari belakang + 1): 7 + 1 = ${selisih}`);
  console.log("Mencetak 10 angka pertama:");

  let deret: number[] = [];
  for (let i = 0; i < jumlah; i++) {
    deret.push(awal + (i * selisih));
  }
  console.log(deret.join(', '));
  console.log("\n");
}

// SOAL 3
const batasAkhirPrima = 37 + 10;

function isPrima(n: number): boolean {
  if (n <= 1) return false;
  if (n <= 3) return true;

  for (let i = 2; i <= Math.sqrt(n); i++) {
    if (n % i === 0) {
      return false;
    }
  }
  return true;
}

function bilanganPrima(batas: number): void {
  console.log("--- Soal 3: Bilangan Prima NIM ---");
  console.log(`Batas Akhir (2 digit terakhir NIM + 10): 37 + 10 = ${batas}`);
  console.log(`Bilangan prima dari 1 sampai ${batas}:`);

  let prima: number[] = [];
  for (let i = 1; i <= batas; i++) {
    if (isPrima(i)) {
      prima.push(i);
    }
  }
  console.log(prima.join(', '));
  console.log("\n");
}

buatSegitiga(tinggiSegitiga);
deretAritmatika(angkaAwal, beda, 10);
bilanganPrima(batasAkhirPrima);
