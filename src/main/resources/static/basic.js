$(document).ready(function () {
    $("#search-button").on("click", function () {
        showWeather();
        setTimeout(function () {
            $("#dataset").css("display", "block");
        }, 500);
    });
    $("#dataset").css("display", "none");
});

function showWeather() {
    const query = $("#zipcode").val();
    const unit = $("#unit option:selected").val();

    $.ajax({
        type: "GET",
        url: `/api/search/oneday?query=${query}&unit=${unit}`,
        success: function (response) {
            $("#data-today").empty();
            const card = generateCardForToday(response);
            $("#data-today").append(card);
        },
    });

    $.ajax({
        type: "GET",
        url: `/api/search/fivedays?query=${query}&unit=${unit}`,
        success: function (response) {
            $("#data-fivedays").empty();
            for (let i = 0; i < response.length; i++) {
                let card = generateCardForFiveDays(response[i]);
                $("#data-fivedays").append(card);
            }
        }
    })
}

function generateCardForToday(response) {
    return `
    <div class="card mb-3">
    <div class="row g-0">
      <div class="col-md-6 d-flex justify-content-center align-items-center today-img-area">
        <img
          src="http://openweathermap.org/img/w/${response.icon}.png"
          class="img-fluid rounded-start"
          alt="icon"
          id="today-img"
        />
      </div>
      <div class="col-md-6">
        <div class="card-body">
          <h5 class="card-title">
            Today in <span id="today-name">${response.name}</span>
          </h5>
          <p class="card-text">
            Weather: <span id="today-temp">${response.main}</span>
          </p>
          <p class="card-text">
            Temperature: <span id="today-temp">${response.temp}</span>
          </p>
          <p class="card-text">
            Humidity: <span id="today-humidity">${response.humidity}</span>
          </p>
          <p class="card-text">Wind: <span id="today-wind">${response.windSpeed}</span></p>
          <p class="card-text">
            <small class="text-muted" id="date-time">Current Time: ${response.datetime}</small>
          </p>
        </div>
      </div>
    </div>
  </div>
    `;
}

function generateCardForFiveDays(response) {
    return `
    <div class="card">
        <div class="d-flex align-items-center">
            <img src="http://openweathermap.org/img/w/${response.icon}.png" class="card-img-top" alt="..." />
            <span class="card-text h6">
                ${response.main}
            </span>
        </div>
        <div class="card-body">
          <p class="card-text">
            Temperature: <span id="today-temp">${response.temp}</span>
          </p>
          <p class="card-text">
            Humidity: <span id="today-humidity">${response.humidity}</span>
          </p>
          <p class="card-text">Wind: <span id="today-wind">${response.windSpeed}</span></p>
          <p class="card-text">
            <small class="text-muted" id="date-time">Date: ${response.datetime}</small>
          </p>
        </div>
    </div>
  `
}
