# Flora

Flora is an open source application, focus on image stylization. And its also a practicing project of 冬.

* mvp
* material design 
* DataBinding & a custom view-binder annotation framework [Ophelia](https://github.com/MashirosBaumkuchen/Ophelia)
* [rxjava2](https://github.com/ReactiveX/RxJava)+[retrofit2](https://square.github.io/retrofit/)+[okhttp3](https://github.com/square/okhttp)
* apply [tensorflow lite](https://github.com/tensorflow/tensorflow), 20+ different style to stylized image
* use IPC to solved OOM problem, the stylized action work on tensor process, which is separated from app process.
* custom multi-type recyclerView
* [fresco](https://github.com/facebook/fresco)
* custom rebound Draggable view
* apply third-part typeface
* day&night theme 
* use IntentService to promote launch speed
* parcelable data
* ...

# show

## display
<div>
  
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/17.png" width="201" height="358" />
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/11.png" width="201" height="358" />
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/15.png" width="201" height="358" />
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/4.png" width="201" height="358" />

</div>

## theme darkness
<div>
  
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/12.png" width="201" height="358" />
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/1.png" width="201" height="358" />

</div>

## stylized page
<div>
  
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/18.png" width="201" height="358" />
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/16.png" width="201" height="358" />
  <img src="https://github.com/MashirosBaumkuchen/Flora/blob/master/screenShots/5.png" width="201" height="358" />

</div>

# api

* tuchong api

* 11/21 apply gank.io api

* Dribbble api is available only to select applications with their approval. Remain in branch [#dribbble](https://github.com/MashirosBaumkuchen/Flora/tree/dribbble), solved code&taken. 

* Now is working in [#master](https://github.com/MashirosBaumkuchen/Flora/tree/master), using another api. 

# notice
launch tensor module before stylized picture.

# license

```
MIT License

Copyright (c) 2018 冬

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
``` 
