import React, { Component } from "react";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import City from "../../components/GoTogether/City";
import { useEffect, useState } from "react";
import httpClient from "../../utils/axios";

export default function GoTogether() {
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
    await httpClient.get("cities/tag").then((res) => setCities(res.data));
  };

  useEffect(() => {
    getCity();
  }, []);

  return (
    <Container>
      <Box>
        <Box sx={{ bgcolor: "white", borderRadius: 1, margin: 3 }}>
          {cities.map((city, index) => {
            return (
              <Box key={index}>
                <City key={index} city={city} value="hiiii" />
              </Box>
            );
          })}
        </Box>
      </Box>
    </Container>
  );
}
