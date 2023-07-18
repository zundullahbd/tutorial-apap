# Tutorial APAP

## Authors

* **Zundullah B. Djibat** - *2006464505* - *APAP SP*

---
## Tutorial 1
### What I have learned today
Saya mempelajari sedikit lebih dalam tentang version control git, terutama pada membuat branch dan merge request, 
membuat pesan commit yang lebih terstruktur serta bagaimana cara revert. Saya juga mempelajari mengenai proses web development menggunakan spring
framework dan menjalankannya. Selain itu saya juga mempelajari bagaimana menggunakan issue tracker di gitlab untuk melacak activity apa saja
yang sudah saya lakukan/akan dilakukan.

### GitLab
#### 1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker? 
Issue Tracker digunakan untuk me-record isu yang dialami pengguna pada suatu aplikasi sehingga masalah tersebut dapat diselesaikan. Selain itu, Issue Tracker dapat memberikan informasi dengan cepat untuk melacak riwayat masalah dan solusi. Issue tracker sendiri bisa dilihat oleh banyak orang tergantung siapa saja yang diberikan akses kedalamnya.

#### 2. Apa perbedaan dari git merge dan git merge --squash?
Git merge dan git merge --squash digunakan sebagai penggabungan branch ke branch utama. Pada git merge --squash sendiri dapat digunakan untuk mempersingkat riwayat git dari branch merge dimana semua perubahan file akan masuk ke branch utama. Maksudnya adalah dengan menggunakan git merge --squash, kita menggabungkan semua history merge menjadi satu dari sebelumnya terdapat merge atau commit kecil-kecil selama proses pengerjaan proyek agar history merge atau commit kita tidak menjadi banyak dan penuh.

#### 3. Apa keunggulan menggunakan Version Control System seperti Git dalam
   pengembangan suatu aplikasi?
Version Control System (VCS) digunakan agar pengembangan aplikasi dapat terhindar dari adanya kerusakan program akibat human error karena VCS dapat melacak perubahan yang dilakukan oleh engineer. Dengan adanya VCS, kita bisa mengetahui siapa saja yang melakukan perubahan didalam file proyek, misalnya melakukan commit. Jika commit tersebut menimbulkan masalah makan dengan VCS kita bisa melakukan pelacakan akar timbulnya masalah dan menyelesaikannya sebelum membesar dan menggagalkan proyek.

### Spring
#### 4. Apa itu library & dependency?
Library adalah program yang terdiri dari banyak fungsi yang tujuannya adalah memudahkan pengembang untuk membuat sebuah aplikasi sedangkan depedency adalah dua atau lebih fungsionalitas yang saling berhubungan dimana suatu fungsi akan mengembalikan informasi yang digunakan fungsi lain. Contoh library adalah seperti java.io.Serializable, biasanya diambil dengan perintah import. Contoh dependency adalah configuration files, database, dll.

#### 5. Apa itu Gradle? Mengapa kita menggunakan Gradle? Apakah ada alternatif dari Gradle?
Gradle adalah build automation tool yang digunakan untuk membangun perangkat lunak guna mengotomatisasi pembuatan aplikasi agar lebih konsisten. Gradle memudahkan pengerjaan suatu proyek yang dilakukan sebuah tim, seperti mendownload semua dependency yang dibutuhkan. Selain gradle, kita bisa menggunakan Maven, Cmake, Meson, dll.

#### 6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?
Contoh lain dari penggunaan spring framework adalah seperti pengaksesan database ataupun membuat RESTful service. Di sisi lain, spring framework digunakan untuk mengonversi aplikasi java menjadi bentuk executable (.exe).

#### 7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?
@PathVariable tidak melakukan encode terhadap value yang diekstrak dari URI, ab+c akan menjadi ab+c. Sementara, value dari @RequestParam di-encode sehingga ab+c akan menjadi ab c. @RequestParam digunakan untuk mengekstrak parameter query, sementara @PathVariable digunakan untuk mengambil data dari URI. @RequestParam sebaiknya digunakan ketika kita memerlukan banyak parameter karena kita bisa mengetahui dengan mudah dan cepat nilai-nilai dari parameter tersebut. Sementara,  @PathVariable bisa kita gunakan ketika parameter yang dibutuhkan lebih sedikit karena nantinya URI path akan menjadi panjang dan kita tidak mengetahui data yang ada akan digunakan oleh parameter yang mana.

### What I did not understand
- [x] Kenapa saya harus belajar APAP?
Agar saya dapat memahami bagaimana infrastruktur/mengkonstruksi aplikasi berbasis enterprise. Selain itu saya juga dapat memahami pengembangan web berbasis spring framework.
- [ ] Kenapa......