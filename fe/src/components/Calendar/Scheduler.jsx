import React, { Component, useState } from 'react'
import { Calendar, dateFnsLocalizer } from 'react-big-calendar';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import startOfWeek from 'date-fns/startOfWeek';
import getDay from 'date-fns/getDay';
import 'react-big-calendar/lib/css/react-big-calendar.css'
import Datepicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { Box } from '@mui/material';
import Paper from '@mui/material/Paper';
import { logDOM } from '@testing-library/react';
import { useDispatch } from 'react-redux';
import { postCalendar } from '../../store/Schedule/schedule';
import { useNavigate } from 'react-router';


const locales = {
  'ko-KR': require('date-fns/locale/ko')
}

const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales
 })

const events = [
  {
    title: 'mom birthday',
    
    start: new Date(2023,0,10),
    end: new Date(2023,0,10)
  },
  {
    title: '가족 여행',
    start: new Date(2023,0,5),
    end: new Date(2023,0,11)
  },
  {
    title: 'ㅋㅋ',
    start: new Date(2023,0,30),
    end: new Date(2023,0,30)
  }
]


export default function Scheduler() {
  const navigate = useNavigate()

  const [newEvent, setNewEvent] = useState({
    title: '', start: '', end: ''
  });
  const [allEvents, setAllEvents] = useState(events)
  const dispatch = useDispatch()
  
  
  function handleAddEvent() {
    setAllEvents([...allEvents, newEvent])
    // console.log(allEvents);
  }
  function handlePlan() {
    const info = {
      title: newEvent.title,
      startDate: newEvent.start,
      endDate: newEvent.end,
      groupId: 1,
      userId: 'podif',
      memo: 'test axios',
      place: 'multicampus'
    }

    // console.log('a')
    if (info.title && info.startDate && info.endDate) {
      dispatch(postCalendar(info))
      // console.log('hello')
    } else {
      alert('빈칸을 채워주세여')
    }
    // console.log('b')
  }

  return (
    <Box>
      <Box>
        <input type="text" placeholder='Add title' style={{width: '20%', marginRight: '10px'}}
          value={newEvent.title} onChange={(e) => setNewEvent({...newEvent, title: e.target.value})}
        />
        <Datepicker placeholderText='시작일' style={{marginRight: '10px'}} 
          selected={newEvent.start} onChange={(start) => setNewEvent({...newEvent, start})}/>
        <Datepicker placeholderText='종료일'
          selected={newEvent.end} onChange={(end) => setNewEvent({...newEvent, end})}
        />
        <button style={{marginTop: '10px'}} onClick={() => {handleAddEvent(); handlePlan(); navigate(0)}}>일정 등록</button>
      </Box>
      <Calendar localizer={localizer} events={allEvents}
      startAccessor='start' endAccessor='end'
      style={{
        height: '500px', margin: '30px',
      }}
      />
    </Box>
  )
}

