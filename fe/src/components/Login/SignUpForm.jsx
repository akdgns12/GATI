import React, { useState, useRef } from "react";
import ReactDOM from "react-dom/client";
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import {
  Container,
  Box,
  TextField,
  Button,
  InputLabel,
  ToggleButtonGroup,
  ToggleButton,
} from "@mui/material";

import { useNavigate } from "react-router";

const contStyle = css`
  max-width: 300px;
  width: 100%;
  margin-top: 33vh;
  background-color: rgba(255, 255, 255, 0.75);
  .form-box {
    width: 100%;
    .header {
      display: flex;
      justify-content: center;
      .title-box {
        display: inline-block;
      }
    }
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
      .double-box {
        display: flex;
        justify-content: space-between;
        .date-form {
          width: 55%;
        }
        .cal-btn {
          width: 40%;
          max-height: inherit;
        }
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

const SignUpForm = () => {
  const [validID, setValidID] = useState(false);
  const [validPW, setValidPW] = useState(false);
  const [currentPW, setCurrentPW] = useState("");
  const [checkedPW, setCheckedPW] = useState(false);
  const [validEmail, setValidEmail] = useState(false);
  const [calType, setCalType] = useState("gregorian");
  const [isLast, setIsLast] = useState(false);

  const navigate = useNavigate();

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
    setCurrentPW(inputPW);
    if (inputPW.length < 5) setValidPW(false);
    else setValidPW(true);
  }

  function comparePW(event) {
    const PWConfirm = event.target.value;
    // console.log(inputRef.current[1]);
    if (currentPW === PWConfirm) {
      setCheckedPW(true);
    } else setCheckedPW(false);
  }

  function checkEmail(event) {
    const emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    const email = event.target.value;
    if (emailRegex.test(email)) {
      setValidEmail(true);
    } else setValidEmail(false);
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (!isLast) {
      toNext();
    } else {
      alert("Registered");
    }
  }

  function toNext() {
    setIsLast(true);
  }

  function toPrev() {
    if (isLast) setIsLast(false);
    else navigate('/login');
  }

  function handleCalType(event, newType) {
    setCalType(newType);
  }

  return (
    <Container css={contStyle}>
      <Box component="form" className="form-box" onSubmit={handleSubmit}>
        <Box className="header">
          <div className="title-box">회원가입</div>
        </Box>
        <Box
          className="labeled-box"
          style={{ display: isLast ? "none" : "block" }}
        >
          <Box className="label-box">
            <InputLabel className="form-label">ID</InputLabel>
            <span
              className="check-msg"
              style={{ color: validID ? "green" : "red" }}
            >
              {validID ? "사용 가능한 ID 입니다." : "사용 불가능한 ID 입니다."}
            </span>
          </Box>
          <TextField
            fullWidth
            className="text-form"
            ref={(el) => (inputRef.current[0] = el)}
            placeholder="ID"
            // error={!validID}
            onChange={checkID}
          />
        </Box>
        <Box
          className="labeled-box"
          style={{ display: isLast ? "none" : "block" }}
        >
          <Box className="label-box">
            <InputLabel className="form-label">비밀번호</InputLabel>
            <span
              className="check-msg"
              style={{ color: validPW ? "green" : "red" }}
            >
              {checkPW
                ? "사용 가능한 비밀번호 입니다."
                : "사용 불가능한 ID 입니다."}
            </span>
          </Box>
          <TextField
            fullWidth
            className="text-form"
            ref={(el) => (inputRef.current[1] = el)}
            type="password"
            name="PW"
            placeholder="Password"
            // error={!validPW}
            onChange={checkPW}
          />
        </Box>
        <Box
          className="labeled-box"
          style={{ display: isLast ? "none" : "block" }}
        >
          <Box className="label-box">
            <InputLabel className="form-label">비밀번호 확인</InputLabel>
            <span
              className="check-msg"
              style={{ color: checkedPW ? "green" : "red" }}
            >
              {checkedPW
                ? "비밀번호가 일치합니다"
                : "비밀번호가 일치하지 않습니다."}
            </span>
          </Box>
          <TextField
            fullWidth
            className="text-form"
            type="password"
            name="PWCheck"
            placeholder="Password Again"
            // error={!checkedPW}
            onChange={comparePW}
          />
        </Box>
        <Box
          className="labeled-box"
          style={{ display: isLast ? "none" : "block" }}
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

        <Box
          className="labeled-box"
          style={{ display: isLast ? "block" : "none" }}
        >
          <Box className="label-box">
            <InputLabel className="form-label">이름</InputLabel>
          </Box>
          <TextField
            required={isLast ? true : false}
            fullWidth
            className="text-form"
            type="text"
            name="nickname"
            placeholder="Nickname"
          />
        </Box>
        <Box
          className="labeled-box"
          style={{ display: isLast ? "block" : "none" }}
        >
          <Box className="label-box">
            <InputLabel className="form-label">전화번호</InputLabel>
          </Box>
          <TextField
            required={isLast ? true : false}
            fullWidth
            className="text-form"
            type="number"
            name="tel"
            placeholder="number"
          />
        </Box>
        <Box
          className="labeled-box"
          style={{ display: isLast ? "block" : "none" }}
        >
          <Box className="label-box">
            <InputLabel className="form-label">생일</InputLabel>
          </Box>
          <Box className="double-box">
            <TextField
              required={isLast ? true : false}
              fullWidth
              className="date-form"
              type="date"
              name="BD"
              placeholder="YYYY-MM-DD"
            />
            <ToggleButtonGroup
              value={calType}
              className="cal-btn"
              onChange={handleCalType}
              exclusive
            >
              <ToggleButton value="gregorian">양력</ToggleButton>
              <ToggleButton value="lunar">음력</ToggleButton>
            </ToggleButtonGroup>
          </Box>
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
            disabled={!validID || !validPW || !checkedPW || !validEmail}
          >
            {isLast ? "완료" : "다음"}
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default SignUpForm;
