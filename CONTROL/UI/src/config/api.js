const BASE_URL = process.env.VUE_APP_API_URL || "https://control.rcsis.ir";
const API_BASE_URL = `${BASE_URL}/api/v1`;
export const API = {
  BASE_URL: `${API_BASE_URL}`,
  LOGIN: `${API_BASE_URL}/admin/authenticate`,
  HOME: {
    SHOW_NEWS: `${API_BASE_URL}/home/show_news`,
    SHOW_SCIENCE_COMMITTEES: `${API_BASE_URL}/home/show_science_committees`,
  },
};
