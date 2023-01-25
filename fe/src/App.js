import "./App.css";
import React from "react";

import NavBar from "./components/NavBar";
import AppBar from "./components/AppBar";


class App extends React.Component {
  render() {
    return (
      <div className="App">
        <NavBar />
        <AppBar />
      </div>
    );
  }
}

export default App;