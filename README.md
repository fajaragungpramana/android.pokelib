# Android PokeLib
[![Android CI Debug](https://github.com/fajaragungpramana/android.pokelib/actions/workflows/app_build_debug.yml/badge.svg)](https://github.com/fajaragungpramana/android.pokelib/actions/workflows/app_build_debug.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
</br>
Dicoding Submission for Menjadi Android Developer Expert Class.

## Information
Language :
- Kotlin

Interface :
- Compose

Dependencies Injection :
- Hilt

Remote Service :
- Retrofit
- Gson

Local Service :
- Room

Design :
- Clean Architecture
- Modularization
- MVVM Pattern
- Repository Pattern
- Localization

## Submission One Requirement
Terdapat 3 Fitur Utama :
- Tema dan API yang digunakan bebas.
- Terdapat halaman list item, detail item, dan list favorite (menggunakan database).
- Semua fitur berjalan dengan lancar tanpa ada force close.

Menerapkan Modularization
- Membuat 1 Android Library untuk core dan 1 dynamic feature untuk favorite.

Menerapkan Clean Architecture
- Tidak melanggar dependency rule dari Clean Architecture.
- Memisahkan model untuk domain dengan model untuk data (separation model).

Menerapkan Dependency Injection
- Harus menggunakan library Dependency Injection berikut : Dagger/Hilt/Koin/Kodein (pilih salah satu).
- Menerapkan dengan tepat untuk melakukan injection untuk semua fungsionalitas.

Menerapkan Reactive Programming
- Boleh menggunakan Rx/Flow (pilih salah satu). 
- Menerapkan dengan tepat untuk mengambil data dari network dan database.

## Submission Two Requirement
Menerapkan Continuous Integration.
- Tool yang digunakan bebas (CircleCI, Github Action, TravisCI, dll).
- Melakukan test dan build APK dengan sukses (pass) pada proses terakhirnya. Lampirkan link project CI pada kolom Catatan ketika akan mengirimkan tugas.

Memiliki performa yang baik
- Menerapkan Leak Canary  dan tidak ada memory leaks saat dianalisa.
- Tidak ada issue terkait performance saat dilakukan Inspect Code.

Menerapkan Security
- Menerapkan obfuscation dengan ProGuard.
- Menerapkan encryption pada database.
- Menerapkan certificate pinning untuk koneksi ke server.
- Tuliskan teknik dan lokasi class pada kolom Catatan untuk mempercepat kami melakukan pengecekan.

Mempertahankan syarat yang ada pada submission sebelumnya.

## Preview
<img src="https://github.com/fajaragungpramana/assets/blob/master/AndroidPokeLib/1688268974864.jpg" width="393" height="852">
<img src="https://github.com/fajaragungpramana/assets/blob/master/AndroidPokeLib/1688268974853.jpg" width="393" height="852">
<img src="https://github.com/fajaragungpramana/assets/blob/master/AndroidPokeLib/1688268974861.jpg" width="393" height="852">
<img src="https://github.com/fajaragungpramana/assets/blob/master/AndroidPokeLib/1688268974857.jpg" width="393" height="852">

## Lincense
Copyright 2023 Fajar Agung Pramana

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

