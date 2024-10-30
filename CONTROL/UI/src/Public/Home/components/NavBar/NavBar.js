export default {
  data() {
    return {
      isSticky: false,
      isMobileMenuOpen: false,
      isDropdownOpen: false,
      activeDropdownIndex: null,
      menuItems: [
        { label: "اعضای هیئت علمی", href: "#faculties" },
        {
          label: "صفحات رسمی",
          subItems: [
            { label: "صفحه اول", href: "/official1" },
            { label: "صفحه دوم", href: "/official2" },
          ],
        },
        {
          label: "آزمایشگاه ها",
          subItems: [
            { label: "آزمایشگاه اول", href: "/lab1" },
            { label: "آزمایشگاه دوم", href: "/lab2" },
          ],
        },
        {
          label: "راهنمای مسائل آموزشی",
          subItems: [
            { label: "کارشناسی", href: "#bachelor" },
            { label: "کارشناسی ارشد", href: "#master" },
            { label: "دکتری", href: "#phd" },
          ],
        },
        { label: "آخرین اخبار", href: "#news" },
      ],
    };
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.handleScroll);
  },
  methods: {
    handleScroll() {
      const stickyThreshold = 100;
      this.isSticky = window.pageYOffset > stickyThreshold;
    },
    updateClientHeight() {
      this.clientHeight = window.innerHeight;
    },
    openDropdown(index) {
      this.isDropdownOpen = true;
      this.activeDropdownIndex = index;
    },
    closeDropdown() {
      this.isDropdownOpen = false;
      this.activeDropdownIndex = null;
    },
    toggleMobileMenu() {
      this.isMobileMenuOpen = !this.isMobileMenuOpen;
    },
    closeMobileMenu() {
      this.isMobileMenuOpen = false;
    },
    scrollToSection(sectionId) {
      const targetElement = document.getElementById(sectionId);
      if (targetElement) {
        const navbarHeight = this.isSticky ? 100 : 0; // Adjust 70 to match the actual height of your sticky navbar
        const targetPosition =
          targetElement.getBoundingClientRect().top +
          window.pageYOffset -
          navbarHeight;
        const startPosition = window.pageYOffset;
        const distance = targetPosition - startPosition;
        const duration = 1000; // duration in milliseconds
        let startTime = null;

        // Start the animation
        requestAnimationFrame((currentTime) =>
          this.animateScroll(
            currentTime,
            startTime,
            startPosition,
            distance,
            duration
          )
        );
      }
    },
    animateScroll(currentTime, startTime, startPosition, distance, duration) {
      if (!startTime) startTime = currentTime;
      const timeElapsed = currentTime - startTime;
      const progress = Math.min(timeElapsed / duration, 1);

      // Custom easing function
      const easeInOutQuad = (t) =>
        t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2;
      const ease = easeInOutQuad(progress);
      window.scrollTo(0, startPosition + distance * ease);

      if (timeElapsed < duration) {
        requestAnimationFrame((currentTime) =>
          this.animateScroll(
            currentTime,
            startTime,
            startPosition,
            distance,
            duration
          )
        );
      }
    },
  },
};
