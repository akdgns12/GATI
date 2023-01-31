import React, { useState, useRef } from "react";
import ReactDOM from "react-dom/client";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Container, Box, TextField, Button, Input } from "@mui/material";

const contStyle = css`
  width: 100%;
  margin-top: 40vh;
  background-color: rgba(192, 192, 192, 0.75);
  .form-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    .text-form {
      margin: 10px;
    }
  }
`;

const SignUpForm = () => {
  const [validID, setValidID] = useState(false);
  const [validPW, setValidPW] = useState(false);
  const [checkedPW, setCheckedPW] = useState(false);
  const [isLast, setIsLast] = useState(false);

  const inputRef = useRef([]);

  function checkID(event) {
    const inputID = event.target.value;
    if (inputID.length < 5) setValidID(false);
    else {
      setValidID(true);
    }
  }

  function checkPW(event) {
    const inputPW = event.target.value;
    if (inputPW.length < 5)
      setValidPW(false);
    else
      setValidPW(true);
  }

  function comparePW(event) {
    const string = event.target.value;
    setCheckedPW(true);
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log(event.target[0].value);
    if (!isLast) {
      toNext();
    }
    else {
      alert("Registered");
    }
  }

  function toNext() {
    setIsLast(true);
  }

  function toPrev() {
    setIsLast(false);
  }

  return (
    <Container css={contStyle}>
      <Box component="form" className="form-box" onSubmit={handleSubmit}>
        SignUp

        <TextField
          className="text-form"
          ref={(el) => inputRef.current[0] = el}
          placeholder="ID"
          error={!validID}
          onChange={checkID}
          style={{ display: isLast ? 'none' : 'block' }}
        />
        <TextField
          className="text-form"
          type="password"
          name="PW"
          placeholder="Password"
          error={!validPW}
          onChange={checkPW}
          style={{ display: isLast ? 'none' : 'block' }}
        />
        <TextField
          className="text-form"
          type="password"
          name="PWCheck"
          placeholder="Password Again"
          error={!checkedPW}
          onChange={comparePW}
          style={{ display: isLast ? 'none' : 'block' }}
        />
        <TextField
          className="text-form"
          required
          type="email"
          name="email"
          placeholder="Email"
          style={{ display: isLast ? 'none' : 'block' }}
        />

        <TextField
          className="text-form"
          type="text"
          name="nickname"
          placeholder="Nickname"
          style={{ display: isLast ? 'block' : 'none' }}
        />
        <TextField
          className="text-form"
          type="number"
          name="tel"
          placeholder="number"
          style={{ display: isLast ? 'block' : 'none' }}
        />

        <Button
          variant="outlined"
          color="primary"
          type="submit"
          disabled={!validID || !validPW || !checkedPW}
        >
          {
            isLast
              ? "Submit"
              : "Next"
          }
        </Button>
        <Button variant="outlined" color="secondary" onClick={toPrev} style={{ display: isLast ? 'block' : 'none' }}>
          back
        </Button>
      </Box>
    </Container>
  );
};

export default SignUpForm;
