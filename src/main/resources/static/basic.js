let tempUnit;
let windSpeed;
let hasError = false;
$(document).ready(function () {
  $("#search-button").on("click", function () {
    $("#loading-spinner").show();
    const query = $("#zipcode").val();
    const length = query.length;
    showData();
    setTimeout(function () {
      $("#loading-spinner").hide();
      if (hasError) {
        $("#dataset").hide();
        $("#errorMessage").show();
      } else {
        $("#dataset").show();
        $("#errorMessage").hide();
      }
    }, 2000);
  });

  $("#edit-button").on("click", function () {
    editUserInfo();
  });

  $("#edit-link").on("click", function () {
    $("#errorMessage-edit").hide();
    $("#search-main").hide();
    $("#dataset").hide();
    $("#edit-area").show();
  });

  $("#loading-spinner").hide();
  $("#edit-area").hide();
  $("#dataset").hide();
  $("#errorMessage").hide();
  $("#errorMessage-edit").hide();
});

function editUserInfo() {
  const id = $("#userid-edit").val();
  const username = $("#username-edit").val();
  const email = $("#email-edit").val();
  const password = $("#password-edit").val();
  const data = { id, username, email, password };
  $.ajax({
    type: "POST",
    url: `/user/edit`,
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (response) {
      console.log("success");
      window.location.reload();
    },
    error: function (response) {
      if (response.responseJSON && response.responseJSON.message) {
        $("#errorMessage-edit").text(response.responseJSON.message);
      } else {
        $("#errorMessage-edit").text("Unknown Error Occurred.");
      }
      $("#errorMessage-edit").show();
    },
  });
}

function showData() {
  const query = $("#zipcode").val();
  const unit = $("#unit option:selected").val();
  tempUnit = unit === "metric" ? "&#8451;" : "&#8457;";
  windSpeed = unit === "metric" ? "m/s" : "mph";

  $.ajax({
    type: "GET",
    url: `/api/search/oneday?query=${query}&unit=${unit}`,
    success: function (response) {
      hasError = false;
      $("#data-today").empty();
      const card = generateCardForToday(response);
      $("#data-today").append(card);
      showFiveDaysWeather();
    },
    error: function (response) {
      hasError = true;
      if (response.responseJSON && response.responseJSON.message) {
        $("#errorMessage").text(response.responseJSON.message);
      } else {
        $("#errorMessage").text("Unknown Error Occurred.");
      }
    },
  });
}

function showFiveDaysWeather() {
  const query = $("#zipcode").val();
  const unit = $("#unit option:selected").val();
  tempUnit = unit === "metric" ? "&#8451;" : "&#8457;";
  windSpeed = unit === "metric" ? "m/s" : "mph";

  $.ajax({
    type: "GET",
    url: `/api/search/fivedays?query=${query}&unit=${unit}`,
    success: function (response) {
      hasError = false;
      $("#data-fivedays").empty();
      for (let i = 0; i < response.length; i++) {
        let card = generateCardForFiveDays(response[i]);
        $("#data-fivedays").append(card);
      }
      showCovidForGlobal();
    },
    error: function (response) {
      hasError = true;
      if (response.responseJSON && response.responseJSON.message) {
        $("#errorMessage").text(response.responseJSON.message);
      } else {
        $("#errorMessage").text("Unknown Error Occurred.");
      }
    },
  });
}

function showCovidForGlobal() {
  $("#data-covid").empty();
  $.ajax({
    type: "GET",
    url: `/api/search/covid/global`,
    success: function (response) {
      hasError = false;
      const tempHtml = generateCardForCovidForGlobal(response);
      $("#data-covid").append(tempHtml);
      showCovidForNational();
    },
    error: function (response) {
      hasError = true;
      if (response.responseJSON && response.responseJSON.message) {
        $("#errorMessage").text(response.responseJSON.message);
      } else {
        $("#errorMessage").text("Unknown Error Occurred.");
      }
    },
  });
}

function showCovidForNational() {
  $.ajax({
    type: "GET",
    url: `/api/search/covid/national`,
    success: function (response) {
      hasError = false;
      const tempHtml = generateCardForCovidForNational(response);
      $("#data-covid").append(tempHtml);
      showCovidForState();
    },
    error: function (response) {
      hasError = true;
      if (response.responseJSON && response.responseJSON.message) {
        $("#errorMessage").text(response.responseJSON.message);
      } else {
        $("#errorMessage").text("Unknown Error Occurred.");
      }
    },
  });
}

function showCovidForState() {
  const query = $("#zipcode").val();
  $.ajax({
    type: "GET",
    url: `/api/search/covid/states?query=${query}`,
    success: function (response) {
      hasError = false;
      const tempHtml = generateCardForCovidForState(response);
      $("#data-covid").append(tempHtml);
    },
    error: function (response) {
      hasError = true;
      if (response.responseJSON && response.responseJSON.message) {
        $("#errorMessage").text(response.responseJSON.message);
      } else {
        $("#errorMessage").text("Unknown Error Occurred.");
      }
    },
  });
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
            Today in <span>${response.name}</span>
          </h5>
          <p class="card-text">
            Weather: <span class="float-end">${response.main}</span>
          </p>
          <p class="card-text">
            Temperature: <span class="float-end">${response.temp}${tempUnit}</span>
          </p>
          <p class="card-text">
            Humidity: <span class="float-end">${response.humidity}%</span>
          </p>
          <p class="card-text">Wind: <span class="float-end">${response.windSpeed}${windSpeed}</span></p>
          <p class="card-text">
            <small class="text-muted">Current Time: ${response.datetime}</small>
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
            Temperature: <span class="float-end">${response.temp}${tempUnit}</span>
          </p>
          <p class="card-text">
            Humidity: <span class="float-end">${response.humidity}%</span>
          </p>
          <p class="card-text">Wind: <span class="float-end">${response.windSpeed}${windSpeed}</span></p>
          <p class="card-text">
            <small class="text-muted">Date: ${response.datetime}</small>
          </p>
        </div>
    </div>
  `;
}

function generateCardForCovidForGlobal(response) {
  return `
  <div class="card">
            <div class="card-body">
              <p class="card-text text-center h6">
                Global Stats
              </p>
              <p class="card-text">
                New Confirmed: <span class="float-end">${response.newConfirmed}</span>
              </p>
              <p class="card-text">
                Total Confirmed: <span class="float-end">${response.totalConfirmed}</span>
              </p>
              <p class="card-text">
                New Deaths: <span class="float-end">${response.newDeaths}</span>
              </p>
              <p class="card-text">
                Total Deaths: <span class="float-end">${response.totalDeath}</span>
              </p>
              <p class="card-text">
                New Recovered: <span class="float-end">${response.newRecovered}</span>
              </p>
              <p class="card-text">
                Total Recovered: <span class="float-end">${response.totalRecovered}</span>
              </p>
              <p class="card-text">
                <small class="text-muted">Date: ${response.date}</small>
              </p>
            </div>
          </div>
  `;
}

function generateCardForCovidForNational(response) {
  return `
  <div class="card">
    <div class="card-body">
      <p class="card-text text-center h6">
        ${response.country}
      </p>
      <p class="card-text">
        New Confirmed: <span class="float-end">${response.newConfirmed}</span>
      </p>
      <p class="card-text">
        Total Confirmed: <span class="float-end">${response.totalConfirmed}</span>
      </p>
      <p class="card-text">
        New Deaths: <span class="float-end">${response.newDeaths}</span>
      </p>
      <p class="card-text">
        Total Deaths: <span class="float-end">${response.totalDeath}</span>
      </p>
      <p class="card-text">
        New Recovered: <span class="float-end">${response.newRecovered}</span>
      </p>
      <p class="card-text">
        Total Recovered: <span class="float-end">${response.totalRecovered}</span>
      </p>
      <p class="card-text">
        <small class="text-muted">Date: ${response.date}</small>
      </p>
    </div>
  </div>
  `;
}

function generateCardForCovidForState(response) {
  return `
    <div class="card">
      <div class="card-body">
        <p class="card-text text-center h6">
          ${response.province}
        </p>
        <p class="card-text">
          Confirmed: <span class="float-end">${response.confirmed}</span>
        </p>
        <p class="card-text">
          Deaths: <span class="float-end">${response.deaths}</span>
        </p>
        <p class="card-text">
          Recovered: <span class="float-end">${response.recovered}</span>
        </p>
        <p class="card-text">
          Active: <span class="float-end">${response.active}</span>
        </p>
        <p class="card-text date-state">
          <small class="text-muted">Date: ${response.date}</small>
        </p>
      </div>
    </div>
  `;
}
