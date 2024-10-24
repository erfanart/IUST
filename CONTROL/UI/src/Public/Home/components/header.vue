<template>
  <div>
    <header class="w-full h-[129px] fixed top-0 z-50">
      <div class="w-full h-[54px] flex justify-between topHeader">
        <img src="@/assets/IUST_wite 2.png" alt="" />
        <img src="@/assets/گروه کنترل دانشکده ی برق.png" alt="" />
      </div>
      <div
        class="bottomHeader w-full relative flex justify-center gap-2 h-[70px] md:h-[60px]"
      >
        <div
          class="h-[35px] xl:hidden rounded-[30px] linkBox text-white flex w-[35px] justify-center items-center cursor-pointer absolute check"
        >
          <a href="#"
            ><i
              class="fa fa-check-square-o hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i
          ></a>
        </div>
        <div
          class="h-[35px] xl:hidden rounded-[30px] linkBox text-white flex w-[35px] justify-center items-center cursor-pointer absolute flask"
        >
          <i
            class="fa fa-flask hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
            aria-hidden="true"
          ></i>
        </div>

        <div
          class="h-[35px] xl:h-[60px] rounded-[30px] xl:w-[916px] linkBox text-white flex w-[35px] xl:cursor-default cursor-pointer !z-[20]"
          id="linkBox"
        >
          <div
            class="xl:hidden bar h-full w-full flex justify-center items-center"
            @click="expand()"
          >
            <i
              class="fa fa-bars hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i>
          </div>
          <ul class="xl:flex justify-around items-center w-full h-full hidden">
            <li class="dp font-bold" @click="opendp1">
              <div
                class="hover:animate-bounce hover:duration-100 hover:text-blue-500 hover:transition-all"
              >
                راهنمای مسائل آموزشی
              </div>
            </li>
            <li
              class="hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 dp font-bold"
              @click="opendp2()"
            >
              آزمایشگاه های گروه کنترل
            </li>
            <li
              class="hover:transition-all hover:animate-bounce hover:duration-100 font-bold hover:text-blue-500"
            >
              <a href="#" class="hover:text-blue-500">صفحات رسمی</a>
            </li>
            <li
              class="hover:transition-all hover:animate-bounce hover:duration-100 font-bold hover:text-blue-500"
            >
              <p
                href="#"
                class="hover:text-blue-500 cursor-pointer"
                @click="gototeachers()"
              >
                اعضای هیات علمی
              </p>
            </li>
            <li
              class="hover:transition-all hover:animate-bounce hover:duration-100 font-bold hover:text-blue-500"
            >
              <p
                href="#"
                class="hover:text-blue-500 cursor-pointer"
                @click="gotoNews()"
              >
                اخرین اخبار
              </p>
            </li>
          </ul>
        </div>
        <div
          dir="rtl"
          class="absolute bottom-[-190px] w-[875px] left-auto right-auto h-0 text-white dpBox rounded-br-[20px] rounded-bl-[20px] overflow-hidden duration-200"
          @mouseleave="closeDpBox"
        >
          <ul
            class="w-full h-full flex items-center flex-wrap-reverse"
            v-if="isdp2"
            id="dpInner"
          >
            <li
              class="w-full hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"
            >
              <a
                href="#"
                class="hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
                >کارشناسی</a
              >
            </li>
            <li
              class="w-full hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"
            >
              <a
                href="#"
                class="hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
                >کارشناسی ارشد</a
              >
            </li>
            <li
              class="w-full hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"
            >
              <a
                href="#"
                class="hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
                >دکتری</a
              >
            </li>
          </ul>
          <ul
            class="w-full h-full flex items-center flex-wrap-reverse"
            v-else
            id="dpInner"
          >
            <li
              class="w-full hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"
            >
              <a
                href="#"
                class="hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
                >آزمایشگاه ابزار دقیق</a
              >
            </li>
            <li
              class="w-full hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"
            >
              <a
                href="#"
                class="hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
                >آزمایشگاه کنترل دیجیتال</a
              >
            </li>
            <li
              class="w-full hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold relative"
            >
              <a
                href="#"
                class="text-white hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
                >آزمایشگاه کنترل صنعتی</a
              >
            </li>
          </ul>
        </div>
        <div
          class="h-[35px] xl:hidden rounded-[30px] linkBox text-white flex w-[35px] justify-center items-center cursor-pointer absolute clip"
        >
          <a href="#">
            <i
              class="fa fa-paperclip hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i>
          </a>
        </div>
        <div
          class="h-[35px] xl:hidden rounded-[30px] linkBox text-white flex w-[35px] justify-center items-center cursor-pointer absolute card"
        >
          <a href="#">
            <i
              class="fa fa-address-card hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i>
          </a>
        </div>
        <div
          class="h-[35px] xl:hidden rounded-[30px] linkBox text-white flex w-[35px] justify-center items-center cursor-pointer absolute news"
        >
          <a href="#">
            <i
              class="fa fa-newspaper-o hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i>
          </a>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
export default {
  name: "SiteHeader",
  data() {
    return {
      isdp2: false,
      BarOpen: false,
    };
  },
  created() {
    window.addEventListener("scroll", this.handleScroll);
    window.addEventListener("resize", this.handleResize);
  },
  destroyed() {
    window.removeEventListener("resize", this.handleResize);
  },
  unmounted() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  create() {
    window.addEventListener("load", this.onWindowLoad);
  },
  methods: {
    opendp1() {
      this.isdp2 = true;
      let dpBox = document.getElementsByClassName("dpBox")[0];
      dpBox.classList.remove("h-0");
      dpBox.classList.add("h-[200.18px]");
    },
    opendp2() {
      this.isdp2 = false;
      let dpBox = document.getElementsByClassName("dpBox")[0];
      dpBox.classList.remove("h-0");
      dpBox.classList.add("h-[200.18px]");
    },
    closeDpBox() {
      let dpBox = document.getElementsByClassName("dpBox")[0];
      dpBox.classList.remove("h-[200.18px]");
      dpBox.classList.add("h-0");
    },
    gotoNews() {
      window.scrollTo(0, window.innerHeight);
    },
    gototeachers() {
      window.scrollTo(0, window.innerHeight * 2);
    },
    handleScroll() {
      let dpBox = document.getElementsByClassName("dpBox")[0];
      let scroll = window.scrollY;
      this.clientHeight = window.innerHeight;
      if (scroll >= this.clientHeight) {
        document.getElementsByClassName("topHeader")[0].style.backgroundColor =
          "#1D4B76";
        document.getElementsByClassName(
          "bottomHeader"
        )[0].style.backgroundColor = "#FFFFFF";
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
        document.getElementsByClassName(
          "bottomHeader"
        )[0].style.backgroundColor = "inherit";
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
    },
    expand() {
      let navIcon = document.getElementsByClassName("bar")[0];
      if (this.BarOpen == false) {
        navIcon.innerHTML = `          <div class="xl:hidden">
            <i
              class="fa fa-times hover:text-blue-500 hover:transition-all hover:animate-bounce hover:duration-100 font-bold"
              aria-hidden="true"
            ></i>
          </div>`;
        document
          .getElementsByClassName("flask")[0]
          .classList.add("md:left-[20%]");
        document.getElementsByClassName("flask")[0].classList.add("md:top-0");
        document.getElementsByClassName("flask")[0].classList.add("top-0");
        document.getElementsByClassName("flask")[0].classList.add("left-[35%]");

        document
          .getElementsByClassName("check")[0]
          .classList.add("md:left-[35%]");
        document.getElementsByClassName("check")[0].classList.add("md:top-0");
        document.getElementsByClassName("check")[0].classList.add("left-[40%]");
        document.getElementsByClassName("check")[0].classList.add("top-8");

        document
          .getElementsByClassName("clip")[0]
          .classList.add("md:left-[60%]");
        document.getElementsByClassName("clip")[0].classList.add("md:top-0");
        document
          .getElementsByClassName("clip")[0]
          .classList.add("left-[46.5%]");
        document.getElementsByClassName("clip")[0].classList.add("top-14");

        document
          .getElementsByClassName("card")[0]
          .classList.add("md:left-[70%]");
        document.getElementsByClassName("card")[0].classList.add("md:top-0");
        document.getElementsByClassName("card")[0].classList.add("left-[53%]");
        document.getElementsByClassName("card")[0].classList.add("top-8");

        document
          .getElementsByClassName("news")[0]
          .classList.add("md:left-[80%]");
        document.getElementsByClassName("news")[0].classList.add("md:top-0");
        document.getElementsByClassName("news")[0].classList.add("left-[58%]");
        document.getElementsByClassName("news")[0].classList.add("top-0");
        this.BarOpen = true;
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
        document
          .getElementsByClassName("flask")[0]
          .classList.remove("md:top-0");
        document.getElementsByClassName("flask")[0].classList.remove("top-0");
        document
          .getElementsByClassName("flask")[0]
          .classList.remove("left-[35%]");

        document
          .getElementsByClassName("check")[0]
          .classList.remove("md:left-[35%]");
        document
          .getElementsByClassName("check")[0]
          .classList.remove("md:top-0");
        document
          .getElementsByClassName("check")[0]
          .classList.remove("left-[40%]");
        document.getElementsByClassName("check")[0].classList.remove("top-8");

        document
          .getElementsByClassName("clip")[0]
          .classList.remove("md:left-[60%]");
        document.getElementsByClassName("clip")[0].classList.remove("md:top-0");
        document
          .getElementsByClassName("clip")[0]
          .classList.remove("left-[46.5%]");
        document.getElementsByClassName("clip")[0].classList.remove("top-14");

        document
          .getElementsByClassName("card")[0]
          .classList.remove("md:left-[70%]");
        document.getElementsByClassName("card")[0].classList.remove("md:top-0");
        document
          .getElementsByClassName("card")[0]
          .classList.remove("left-[53%]");
        document.getElementsByClassName("card")[0].classList.remove("top-8");

        document
          .getElementsByClassName("news")[0]
          .classList.remove("md:left-[80%]");
        document.getElementsByClassName("news")[0].classList.remove("md:top-0");
        document
          .getElementsByClassName("news")[0]
          .classList.remove("left-[58%]");
        document.getElementsByClassName("news")[0].classList.remove("top-0");
        this.BarOpen = false;
      }
    },
  },
};
</script>

<style>
header {
  background: linear-gradient(
    180deg,
    #000000 0%,
    rgba(51, 51, 51, 0.7) 54.1%,
    rgba(102, 102, 102, 0) 100%
  );
}

.linkBox {
  z-index: 2;
  backdrop-filter: blur(2px);
  background: #00000066;
}

.imgBox {
  background-image: url("@/assets/image 9.png");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

.dpBox {
  background: #00000066;
  z-index: 1;
}

.dpBox ul li a {
  margin-right: 10px;
}

.picBox {
  box-shadow: rgba(0, 0, 0, 0.25) 0px 14px 28px,
    rgba(0, 0, 0, 0.22) 0px 10px 10px;
}

ul {
  list-style: none;
}
a {
  text-decoration: none;
  color: inherit !important;
}
</style>
