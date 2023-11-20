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



![Ekran görüntüsü 2023-11-19 142622](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/1f6ad690-6bfa-423f-a377-bdb67bea4763)



![Ekran görüntüsü 2023-11-19 142613](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/db1aad27-591a-4c63-9035-a6bdd0897d8b)



![Ekran görüntüsü 2023-11-19 142608](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/1752db91-c4bf-49ac-994b-e32176a0dce5)



![Ekran görüntüsü 2023-11-19 142603](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/38402a8b-6e3b-4853-9cbb-f3edffe10d1d)



![Ekran görüntüsü 2023-11-19 142557](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/0164bcc6-e429-464d-bb8c-cfed6b1805f2)



![Ekran görüntüsü 2023-11-19 142551](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/918149d8-595e-4b60-abc7-75fa4f540b6a)



![Ekran görüntüsü 2023-11-19 142544](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/1a831f70-397e-4d1a-bf97-ddb580eddc7d)



![Ekran görüntüsü 2023-11-19 142537](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/28c209c8-c6e9-4f9a-8de5-7f549a247f65)



![Ekran görüntüsü 2023-11-19 142526](https://github.com/Cntrk01/FoodAppCleanArchitecture/assets/98031686/89b09cd9-99b1-4f96-a3f9-fd27e8f21cd0)

