import { Container } from "@mui/system";
import React from "react";

import Lottie from "react-lottie-player";
import error from "../static/error.json";

export default function Error() {
  return (
    <Container>
      <Lottie loop animationData={error} play style={{ margin: "0 auto" }} />
    </Container>
  );
}
