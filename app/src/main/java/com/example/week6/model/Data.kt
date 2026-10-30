package com.example.week6.model

object Data {
    val profile = StudentProfile(
        name = "Michael Kent William",
        major = "Teknik Informatika AI - Angkatan 2025",
        nim = "NIM. 1076012510037"
    )

    val initialCommunities = listOf(
        Community("Prog", "Programmer", "Coding & Logika", "Belajar algoritma dan bahasa pemrograman.", 150),
        Community("Net", "Jaringan", "Networking & Server", "Fokus pada instalasi jaringan dan keamanan.", 90),
        Community("Data", "Data Science", "Analisis Data", "Mengolah data menjadi informasi penting.", 110),
        Community("Design", "UI/UX", "Desain Aplikasi", "Merancang tampilan aplikasi agar mudah dipakai.", 85),
        Community("Web", "Web Dev", "Bikin Website", "Belajar membuat website frontend dan backend.", 130),
        Community("Game", "Game Dev", "Bikin Game", "Wadah kreatif membuat game 2D dan 3D.", 75)
    )

    val initialProjects = listOf(
        Project(
            id = "proj-web-01",
            communityId = "Web",
            title = "Web Sekolah",
            description = "Membuat website profil sekolah dasar dengan HTML, CSS, dan JavaScript.",
            techStack = listOf("HTML", "CSS", "JavaScript"),
            status = "In Progress"
        ),
        Project(
            id = "proj-prog-01",
            communityId = "Prog",
            title = "Aplikasi Kasir",
            description = "Aplikasi hitung belanja sederhana berbasis desktop untuk toko kelontong.",
            techStack = listOf("Java", "MySQL"),
            status = "Completed"
        )
    )
}
