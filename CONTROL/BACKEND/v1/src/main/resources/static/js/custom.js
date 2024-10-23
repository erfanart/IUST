let dpBox = document.querySelector(".dpBox");

document.getElementsByClassName("dp")[0].onclick = function () {
  dpBox.classList.remove("h-0");
  dpBox.classList.add("h-[200.18px]");
  dpBox.innerHTML = `               <ul
            class="w-full h-full flex items-center flex-wrap-reverse"
          >
            <li class="w-full  hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"><a href="#" >کارشناسی</a></li>
            <li class="w-full  hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"><a href="#" >کارشناسی ارشد</a></li>
            <li class="w-full  hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"><a href="#" >دکتری</a></li>
          </ul>
          `;
};
document.getElementsByClassName("dp")[1].onclick = function () {
  dpBox.classList.remove("h-0");
  dpBox.classList.add("h-[200.18px]");
  dpBox.innerHTML = `               <ul
            class="w-full h-full flex items-center flex-wrap-reverse"
          >
            <li class="w-full  hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"><a href="#" >آزمایشگاه ابزار دقیق</a></li>
            <li class="w-full  hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"><a href="#" >آزمایشگاه کنترل دیجیتال</a></li>
            <li class="w-full  hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"><a href="#" >آزمایشگاه کنترل صنعتی</a></li>
          </ul>
          `;
};
dpBox.onmouseleave = function () {
  dpBox.classList.remove("h-[200.18px]");
  dpBox.classList.add("h-0");
};
let BarOpen = false;
let navIcon = document.getElementsByClassName("bar")[0];
//for responsive mode on mobile and tablets
navIcon.onclick = function () {
  if (BarOpen == false) {
    navIcon.innerHTML = `          <div class="xl:hidden">
            <i
              class="fa fa-times hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i>
          </div>`;
    document.getElementsByClassName("flask")[0].classList.add("md:left-[20%]");
    document.getElementsByClassName("flask")[0].classList.add("md:top-0");
    document.getElementsByClassName("flask")[0].classList.add("top-0");
    document.getElementsByClassName("flask")[0].classList.add("left-[35%]");

    document.getElementsByClassName("check")[0].classList.add("md:left-[35%]");
    document.getElementsByClassName("check")[0].classList.add("md:top-0");
    document.getElementsByClassName("check")[0].classList.add("left-[40%]");
    document.getElementsByClassName("check")[0].classList.add("top-8");

    document.getElementsByClassName("clip")[0].classList.add("md:left-[60%]");
    document.getElementsByClassName("clip")[0].classList.add("md:top-0");
    document.getElementsByClassName("clip")[0].classList.add("left-[46.5%]");
    document.getElementsByClassName("clip")[0].classList.add("top-14");

    document.getElementsByClassName("card")[0].classList.add("md:left-[70%]");
    document.getElementsByClassName("card")[0].classList.add("md:top-0");
    document.getElementsByClassName("card")[0].classList.add("left-[53%]");
    document.getElementsByClassName("card")[0].classList.add("top-8");

    document.getElementsByClassName("news")[0].classList.add("md:left-[80%]");
    document.getElementsByClassName("news")[0].classList.add("md:top-0");
    document.getElementsByClassName("news")[0].classList.add("left-[58%]");
    document.getElementsByClassName("news")[0].classList.add("top-0");
    BarOpen = true;
  } else {
    navIcon.innerHTML = `          <div class="xl:hidden">
            <i
              class="fa fa-bars hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i>
          </div>`;

    document
      .getElementsByClassName("flask")[0]
      .classList.remove("md:left-[20%]");
    document.getElementsByClassName("flask")[0].classList.remove("md:top-0");
    document.getElementsByClassName("flask")[0].classList.remove("top-0");
    document.getElementsByClassName("flask")[0].classList.remove("left-[35%]");

    document
      .getElementsByClassName("check")[0]
      .classList.remove("md:left-[35%]");
    document.getElementsByClassName("check")[0].classList.remove("md:top-0");
    document.getElementsByClassName("check")[0].classList.remove("left-[40%]");
    document.getElementsByClassName("check")[0].classList.remove("top-8");

    document
      .getElementsByClassName("clip")[0]
      .classList.remove("md:left-[60%]");
    document.getElementsByClassName("clip")[0].classList.remove("md:top-0");
    document.getElementsByClassName("clip")[0].classList.remove("left-[46.5%]");
    document.getElementsByClassName("clip")[0].classList.remove("top-14");

    document
      .getElementsByClassName("card")[0]
      .classList.remove("md:left-[70%]");
    document.getElementsByClassName("card")[0].classList.remove("md:top-0");
    document.getElementsByClassName("card")[0].classList.remove("left-[53%]");
    document.getElementsByClassName("card")[0].classList.remove("top-8");

    document
      .getElementsByClassName("news")[0]
      .classList.remove("md:left-[80%]");
    document.getElementsByClassName("news")[0].classList.remove("md:top-0");
    document.getElementsByClassName("news")[0].classList.remove("left-[58%]");
    document.getElementsByClassName("news")[0].classList.remove("top-0");
    BarOpen = false;
  }
};
//scroll event for change header
window.onload = function () {
  window.onscroll = function () {
    let scroll = this.scrollY;
    console.log(scroll);
    if (scroll >= document.body.clientHeight) {
      document.getElementsByClassName("topHeader")[0].style.backgroundColor =
        "#1D4B76";
      document.getElementsByClassName("bottomHeader")[0].style.backgroundColor =
        "#FFFFFF";
      for (let i = 0; i < 6; i++) {
        document.getElementsByClassName("linkBox")[i].style.backgroundColor =
          "#FFFFFF";
        document.getElementsByClassName("linkBox")[i].style.color = "#000";
      }
      dpBox.style.backgroundColor = "#FFFFFF";
      dpBox.classList.remove("text-white");
      dpBox.style.boxShadow =
        " rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px";
    } else {
      document.getElementsByClassName("topHeader")[0].style.backgroundColor =
        "inherit";
      document.getElementsByClassName("bottomHeader")[0].style.backgroundColor =
        "inherit";
      for (let i = 0; i < 6; i++) {
        document.getElementsByClassName("linkBox")[i].style.backgroundColor =
          "#00000066";
        document.getElementsByClassName("linkBox")[i].style.color = "";
      }
      dpBox.style.backgroundColor = "#00000066";
      dpBox.classList.add("text-white");
      dpBox.style.boxShadow =
        " rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px";
    }
  };
};
//news box
let info = document.getElementsByClassName("information")[0];
info.onmouseover = () => {
  document.getElementsByClassName("cover")[0].style.display = "flex";
};
info.onmouseleave = () => {
  document.getElementsByClassName("cover")[0].style.display = "none";
};
