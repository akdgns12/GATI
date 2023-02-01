import { React, useState } from "react";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Box, Button, Container, TextField, InputLabel } from "@mui/material";

import { useNavigate } from "react-router";

const contStyle = css`
  max-width: 300px;
  width: 100%;
  margin-top: 33vh;
  border-radius: 10px;
  background-color: rgba(255,255,255, 075);
  .form-box{
    width: 100%;
    .labeled-box {
      margin-top: 8px;
      .label-box {
        display: flex;
        justify-content: space-between;
        .form-label {

        }
        .check-msg {
          font-size: x-small;
        }
      }
      .text-form {
        margin-top: 2px;
      }
    }
    .btn-group {
      margin-top: 10px;
      .submit-btn {
        margin: 5px;
      }
      .prev-btn {
        margin: 5px;
      }
    }
  }
`;

const FindID = () => {
  const [validEmail, setValidEmail] = useState(false);

  const navigate = useNavigate();

  function checkEmail(event) {
    const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    const email = event.target.value;
    if (emailRegex.test(email)) {
      setValidEmail(true);
    } else setValidEmail(false);
  }

  function toPrev() {
    navigate("/login");
  }

  function submitForm(event) {
    event.preventDefault();
    console.log(event.target.email.value);
  }

  return (
    <Container css={contStyle}>
      <Box component="form" className="form-box" onSubmit={submitForm}>
        <h1>FORGOT ID?</h1>
        <Box
          className="labeled-box"
        >
          <Box className="label-box">
            <InputLabel className="form-label">E-mail</InputLabel>
            <span
              className="check-msg"
              style={{ color: validEmail ? "green" : "red" }}
            >
              {validEmail
                ? "유효한 Email 입니다."
                : "유효하지 않은 형식입니다."}
            </span>
          </Box>
          <TextField
            required
            fullWidth
            className="text-form"
            type="email"
            name="email"
            placeholder="Email"
            onChange={checkEmail}
          />
        </Box>
        <Box className="btn-group">
          <Button
            variant="outlined"
            color="primary"
            className="prev-btn"
            onClick={toPrev}
          >
            이전
          </Button>
          <Button
            variant="outlined"
            color="primary"
            type="submit"
            className="submit-btn"
            disabled={!validEmail}
          >
            다음
          </Button>
        </Box>
      </Box>

    </Container>
  )
}

export default FindID;