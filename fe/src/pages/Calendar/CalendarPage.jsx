

import Scheduler from "../../components/Calendar/Scheduler";
import Plans from "../../components/Calendar/Plans";

import Box from '@mui/material/Box';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';

import { useNavigate } from "react-router";
import React, {useState} from "react";
import ReactDOM from "react-dom/client";
import httpClient from "../../utils/axios";

export default function Calendar() {
  // mui 기본 토글버튼 스타일 효과주는 역할임
  const [alignment, setAlignment] = React.useState('web');
  const handleChange = (
    event: React.MouseEvent<HTMLElement>,
    newAlignment: string,
  ) => {
    setAlignment(newAlignment);
  };
  // 버튼 누르면 달력 보이고 안보이게 하는 기능
  const [show, setShow] = useState(true)
  // 포스트 후에 리렌더링
  const navigate = useNavigate()

  // 일정 버튼 누르면 get 요청 보내기
  const [planData, setPlanData] = useState([])
 
  async function getPlan(groupId, success, fail) {
    const response = await httpClient.get(`/plan/${groupId}`).then(success).catch(fail)
    return response
  }

  function handleGetPlan() {
    const getPlanDatas = async () => {
      await getPlan(1, (response) => {return response.data}, (e) => {console.log(e)}).then((data) => setPlanData(data.result))
    };
    getPlanDatas()
  }

  

  return (
    <Box>
      <ToggleButtonGroup
        color="primary"
        value={alignment}
        exclusive
        onChange={handleChange}
        aria-label="Platform"
        size='large'
        sx={{
          margin: 2,
          border: 1,
        }}
        >
        <ToggleButton value="plans" onClick={() => (setShow(false), handleGetPlan())}>일정</ToggleButton>
        <ToggleButton value="calendar" onClick={() => setShow(true)}>달력</ToggleButton>
      </ToggleButtonGroup>
      {show? <Scheduler/>:<Plans planData={planData}/>}
    </Box>
  )
}


