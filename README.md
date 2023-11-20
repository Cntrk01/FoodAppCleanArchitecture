# FoodAppCleanArchitecture
Bu projede clean architecture prensiplerine dikkat ederek proje geliştirdim.MVVM mimarisini kullandım.Projemde tamamen Flow kullandım ve Flow kullanarak bildiklerimi pekiştirmeye çalıştım.
Listelenen yemekleri favoriye ekleme işlemini gerçekleştirdim.Favoriye eklenen yemeği tıklandığında tekrar api isteği değilde roomda varsa eğer roomdan getirerek apiye sürekli istek atmamayı hedefledim.
Apiden veya roomdan gelen verileri statelerde tutarak kullanıcının ekranına getirdim.Combine Flow yapısını ilk defa kullanarak öğrendim.Okhttp kullanarak verilerin işlenme durumunu loglayarak hata durumlarını takip ettim
Base Fragment kullanarak ViewBinding işlemlerini yaptım böylelikle her seferinde viewBinding tanımlamadan kaçındım.Apilerle çalışarak veri çekme durumlarını pekiştirdim.Gereksiz Fragment oluşturmaktan kaçındım.
Olabildiğince fragment içerisinde gereksiz tanımlamalardan kaçındım.Bazı noktalarda oluşturduğum viewmodelin scopunu kullanmam gerekti.Buda flow içerisinde var olan veriyi collect edebilmem içindi.
Response classını kullanarak Succes,Error,Loading durumlarını daha kolay bir şekilde ele aldım ve kullanıcıya ona göre bilgi verdim.
Beni zorlayan kısım detay sayfasıydı.Orada bir yemek eğer roomda kayıtlıysa oradan getirmesini değilse apiye istek atmasını yaptım.Çünkü kayıtlı veriyi her seferinde apiden getirmesi mantıklı değildi.
Argümanları kullanmadan verileri lokal databaseden dinamik bir şekilde detay sayfasında getirdim.Amacım kayıtlı olan verileri argüman,serilizable kullanmadan listelemekti.Amacıma ulaştım.



-Retrofit


-Room


-State


-Flow


-Hilt


-WebView


-UseCase


-OkHtpp



![Ekran görüntüsü 2023-11-19 142608](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/39daa2db-ab30-4968-a20e-934a03313570)






![Ekran görüntüsü 2023-11-19 142603](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/91112cab-c3a9-4f2b-9055-46e24bee73f9)





![Ekran görüntüsü 2023-11-19 142557](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/0116b049-49ad-4157-a5ae-364bd06fd4e3)






![Ekran görüntüsü 2023-11-19 142551](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/1f902872-011f-424d-b236-89cfc46f7d36)






![Ekran görüntüsü 2023-11-19 142544](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/3a0ae0b0-c388-456f-89bd-533c5ac3da5e)






![Ekran görüntüsü 2023-11-19 142537](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/8485705a-6ad1-494e-bf82-4e4ecb5eda14)






![Ekran görüntüsü 2023-11-19 142526](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/6dee0417-08e6-4bfa-9e53-88a7e16b328e)








![Ekran görüntüsü 2023-11-19 142622](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/d1f5b97b-9980-4e14-9048-a6b259c4bdb4)








![Ekran görüntüsü 2023-11-19 142613](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/40b02480-ef17-468a-aacf-fedb4727533c)
