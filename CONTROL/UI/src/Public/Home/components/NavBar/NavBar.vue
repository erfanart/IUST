<template>
  <header class="w-full h-[129px] fixed top-0 z-50">
    <nav
      :class="[
        isSticky
          ? 'fixed top-0 w-full bg-blue-950 text-white shadow-md z-50'
          : 'mt-5 mx-auto rounded-full bg-black/50 backdrop-blur-lg w-11/12',
        'flex p-4',
      ]"
    >
      <div class="flex items-center justify-center w-full">
        <!-- Left Logo (Sticky Mode) -->
        <li v-if="isSticky" class="flex mx-auto justify-end">
          <img
            src="@/assets/IUST_wite 2.png"
            alt="Left Logo"
            class="h-10 size"
          />
        </li>

        <!-- Navbar Menu -->
        <li
          v-for="(item, index) in menuItems"
          :key="index"
          class="relative group list-none"
        >
          <div v-if="item.subItems" class="relative">
            <a
              class="text-white font-semibold cursor-pointer px-4 py-2 block no-underline"
              @mouseenter="openDropdown(index)"
            >
              {{ item.label }}
            </a>
            <li
              dir="rtl"
              :class="[
                'absolute  w-[90vw] max-w-[875px] transition-all duration-200 opacity-0',
                activeDropdownIndex === index && isDropdownOpen
                  ? 'opacity-100 mt-4 rounded-bl-[30px] rounded-br-[30px]  overflow-visible h-[200.18px]'
                  : 'h-0 w-0 overflow-hidden',
                isSticky ? 'bg-white text-black ' :  'bg-black/50 text-white',
                
              ]"
              @mouseleave="closeDropdown" 
              style="
                left: 50%;
                transform: translateX(-50%);
                /* padding-top: 10px; */
                top: 100%;
              "
            >
              <!-- Submenu -->
              <ul
                
                v-for="(subItem, subIndex) in item.subItems"
                :key="subIndex"
                class="px-4 py-2 hover:bg-black/40 list-none text-right"
              >
                <a
                  :href="subItem.href"
                  @click.prevent="scrollToSection(subItem.href.substring(1))"
                  class="block no-underline"
                >
                  {{ subItem.label }}
                </a>
              </ul>
            </li>
          </div>

          <a
            v-else
            :href="item.href"
            class="text-white font-semibold transition hover:text-blue-300 px-4 py-2 block no-underline"
            @click.prevent="scrollToSection(item.href.substring(1))"
          >
            {{ item.label }}
          </a>
        </li>

        <!-- Right Logo (Sticky Mode) -->
        <li v-if="isSticky" class="flex mx-auto justify-start">
          <img
            src="@/assets/گروه کنترل دانشکده ی برق.png"
            alt="Right Logo"
            class="h-10"
          />
        </li>
      </div>
    </nav>
  </header>
</template>

<script src="./NavBar.js" />
<style scoped src="./NavBar.css" />
