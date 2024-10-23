// import api from "@/Public/api.json";

function buildApi(base, obj) {
  let result = {};
  

  for (let key in obj) {
      if (typeof obj[key] === 'object' && key !== 'base') {

        result[key.toUpperCase()] = buildApi(base + (obj[key].base || ''), obj[key]);
      } else if (key == 'base') {

        result[key.toUpperCase()] = base;
      } else if (key !== 'base') {

        result[key.toUpperCase()] = base + obj[key];
      }
  }
  
  return result;
}

async function fetchApiConfig(location) {
  try {
      const response = await fetch('api.json'); // Assuming this file is in the public folder
      const apiRoutes = await response.json(); // Read the JSON file
      const apiObject = buildApi(apiRoutes.base,apiRoutes); // Build the API object

      apiObject[location.toUpperCase()];
      return apiObject;
  } catch (error) {
      console.error("Error fetching API config:", error);
      return null; // Return null or a default object in case of error
  }
}
export { fetchApiConfig };
// const API = buildApi(api.base,api );

// export default API;
