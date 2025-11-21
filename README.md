>>>Note App (Kotlin + Jetpack Compose + Room + MVVM)

এই প্রজেক্টটি একটি Simple Note Taking App, যেখানে আমি মূলত Room Database কীভাবে কাজ করে তা প্র্যাকটিস করেছি।
পুরো অ্যাপটি বানানো হয়েছে Kotlin, Jetpack Compose, Room Database, MVVM, এবং Compose Navigation ব্যবহার করে।

>>>প্রজেক্টের উদ্দেশ্য

এই অ্যাপের প্রধান লক্ষ্য ছিল:
Room Database সম্পূর্ণভাবে বোঝা
CRUD অপারেশন প্র্যাকটিস করা
Jetpack Compose দিয়ে UI তৈরি করা
MVVM আর্কিটেকচারের পরিষ্কার ধারণা নেওয়া
Navigation ব্যবহার করে স্ক্রিন পরিবর্তন করা

>>>ফিচারসমূহ:
সার্চ ফিচার
নতুন নোট যোগ করা (Add)
পুরাতন নোট এডিট করা (Edit)
নোট মুছে ফেলা (Delete)
সব নোট দেখানো (List)
রিয়েল-টাইম আপডেট (Flow ব্যবহার করা হয়েছে)
MVVM আর্কিটেকচার
সম্পূর্ণ Jetpack Compose UI
Compose Navigation

>>>ব্যবহৃত প্রযুক্তি (Tech Stack)
Kotlin
Jetpack Compose
Room Database
MVVM + Repository Pattern
Coroutines + Flow
Compose Navigation

>>>অ্যাপ আর্কিটেকচার
  UI (Compose Screen)
      ↓
   ViewModel
      ↓
  Repository
      ↓
     DAO
      ↓
Room Database (SQLite)

>>>ডেটা চলাচল:
UI → ViewModel → Repository → DAO → Database
Database → DAO Flow → Repository → ViewModel → UI

>>>প্রজেক্ট স্ট্রাকচার
app/
 ├─ data/
 │   ├─ local/
 │   │   ├─ Note.kt
 │   │   ├─ NoteDao.kt
 │   │   └─ NoteDatabase.kt
 │   └─ repository/
 │       └─ NoteRepository.kt
 ├─ viewmodel/
 │   └─ NoteViewModel.kt
 ├─ ui/
 │   └─ screens/
 │       ├─ NoteListScreen.kt
 │       └─ AddEditNoteScreen.kt
 └─ navigation/
     └─ NavGraph.kt

