import React, { Component } from "react";
import ReactDOM from "react-dom/client";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import City from "../../components/GoTogether/City";
import { useSelector, useDispatch } from "react-redux";
import { useEffect, useState } from "react";
import axios from "axios";
import { Card } from "@mui/material";

export default function GoTogether() {
  const dispatch = useDispatch();

  const [cities, setCities] = useState([
    {
      id: -1,
      tag: "",
      tagId: -1,
      tagCnt: -1,
      sigungucode: 0,
      recommandDtos: [
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
        {
          firstimage: "",
          title: "",
        },
      ],
    },
  ]);

  const getCity = async () => {
    await axios
      .get("https://i8a805.p.ssafy.io/api/cities/tag")
      .then((res) => setCities(res.data));
  };

  useEffect(() => {
    getCity();
  }, []);

  return (
    <Container>
      <Box sx={{ height: "100vh" }}>
        <Box sx={{ bgcolor: "white", borderRadius: 1, margin: 3 }}>
          {cities.map((city, index) => {
            return (
              <Card>
                <City key={index} city={city} />;
              </Card>
            );
          })}
        </Box>
      </Box>
    </Container>
  );
}
