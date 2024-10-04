<template>
  <div>
    <footer class="w-full h-[553px] font-[main]">
      <div class="mainFooter w-full h-[353px] p-[30px] flex justify-between">
        <div class="w-[25%] sm:w-[18%] md:w-[25%] xl:w-[30%] h-full">
          <ul class="h-full w-full flex flex-wrap">
            <li
              class="w-full h-1/5 xl:text-[30px] md:text-[20px] text-[11px] font-bold text-center"
            >
              :صفحات رسمی گروه
            </li>
            <li
              class="w-full h-1/5 justify-center xl:text-[20px] md:text-[15px] text-[11px] flex items-center p-2"
            >
              <a
                href="tel:(+9821)73225600"
                class="flex hover:animate-pulse gap-1 items-center"
                ><i class="fa fa-phone text-slate-600" aria-hidden="true"></i>
                <p>(+9821)73225600</p>
              </a>
            </li>
            <li
              class="w-full h-1/5 justify-center xl:text-[20px] md:text-[15px] text-[11px] flex items-center p-2"
            >
              <a
                href="tel:(+9821)77240302"
                class="flex hover:animate-pulse gap-1 items-center"
                ><i class="fa fa-fax text-slate-600" aria-hidden="true"></i>
                <p>(+9821)73225600</p>
              </a>
            </li>
            <li
              class="w-full h-1/5 justify-center xl:text-[20px] md:text-[15px] text-[11px] flex items-center p-2"
            >
              <div class="flex hover:animate-pulse gap-1 items-center">
                <i
                  class="fa fa-map-marker text-slate-600"
                  aria-hidden="true"
                ></i>
                <p>
                  تهران، میدان رسالت، خیابان هنگام، دانشگاه علم و صنعت ایران،
                  دانشکده مهندسی برق
                </p>
              </div>
            </li>

            <li
              class="w-full h-1/5 justify-center xl:text-[20px] md:text-[15px] text-[11px] flex items-center p-2"
            >
              <a href="#" class="flex items-center hover:animate-pulse gap-1"
                ><i
                  class="fa fa-instagram text-slate-600"
                  aria-hidden="true"
                ></i>
                <p>somthig</p>
              </a>
            </li>
          </ul>
        </div>
        <div class="w-[50%] h-full flex justify-between flex-wrap" dir="rtl">
          <div class="xl:w-[28%] md:w-[30%] w-[40%]">
            <ul class="w-full h-full">
              <li
                class="w-full h-[20%] text-right xl:text-[18px] text-[14px] font-bold"
              >
                آزمایشگاه ها:
              </li>
              <li class="w-full h-[25%] text-right">
                <a href="#" class="xl:text-[17px] text-[13px]">
                  آزمایشگاه ابزار های دقیق
                </a>
              </li>
              <li class="w-full h-[25%] text-right">
                <a href="#" class="xl:text-[17px] text-[13px]">
                  آزمایشگاه کنترل های دیحیتال</a
                >
              </li>
              <li class="w-full h-[25%] text-right">
                <a href="#" class="xl:text-[17px] text-[13px]">
                  آزمایشگاه کنترل صنعتی</a
                >
              </li>
            </ul>
          </div>
          <div class="xl:w-[28%] w-[30%] text-right">
            <p class="font-bold xl:text-[18px] text-[14px]">ارتباط با صنعت:</p>
          </div>
          <div class="xl:w-[28%] w-[30%] text-right">
            <p class="font-bold xl:text-[18px] text-[14px]">آخرین اخبار:</p>
          </div>
        </div>
      </div>
      <div class="fakeBox"></div>
    </footer>
  </div>
</template>
<script>
export default {
  name: "siteFooter",
};
</script>
<style scoped   >
</style>