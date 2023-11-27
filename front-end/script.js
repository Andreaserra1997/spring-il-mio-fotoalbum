const apiUrl = "http://localhost:8080/api/v1/photos";
const apiUrlMessage = "http://localhost:8080/api/v1/messages";
const root = document.getElementById("root");

const searchByTitle = async () => {
  const titleInput = document.getElementById("searchInput").value;
  try {
    const response = await axios.get(apiUrl, {
      params: {
        search: titleInput,
      },
    });
    renderPhotoList(response.data);
  } catch (error) {
    console.log(error);
  }
};

const handleSearchForm = (event) => {
  event.preventDefault();
  const titleInput = document.getElementById("searchInput").value;
  searchByTitle(titleInput);
};

const renderPhoto = (element) => {
  return `
        <div class="text-center">
            <img src="${element.url}" alt="photo" style="width: 200px;">
            <div>
                <h5 class="card-title">${element.title}</h5>
                <p class="card-text"">${element.description}</p>
            </div>
        </div>`;
};

const renderPhotoList = (data) => {
  let content;
  if (data.length > 0) {
    content = '<div class="row">';
    data.forEach((element) => {
      content += '<div class="col-3">';
      content += renderPhoto(element);
      content += "</div>";
    });
    content += "</div>";
  } else {
    content = '<div class="alert alert-info">La lista è vuota</div>';
  }
  root.innerHTML = content;
};

const getPhotos = async () => {
  try {
    const response = await axios.get(apiUrl);
    renderPhotoList(response.data);
  } catch (error) {
    console.log(error);
  }
};

getPhotos();

document
  .getElementById("messageForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const message = document.getElementById("message").value;
    try {
      await axios.post(apiUrlMessage, {
        email,
        message,
      });
      successMessage.style.display = "block";
      this.style.display = "none";
      setTimeout(() => {
        window.location.href = "index.html";
      }, 2000);
    } catch (error) {
      console.error(error);
    }
  });
