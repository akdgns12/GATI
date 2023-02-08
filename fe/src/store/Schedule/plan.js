import React from "react";
import httpClient from "../../utils/axios";


async function getPlan() {
  try {
    const response = await httpClient.get('/plan', {
      params: {
        groupId : 1
      }
    })
  }
}
