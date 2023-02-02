import {React, useState}  from 'react'
import { useSelector, useDispatch } from 'react-redux'
import PlanCard from './PlanCard';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';

export default function Plans() {
  const {planData} = useSelector((state) => state.schedule)

  return (
    <div>
      {planData.map((plan, index) => {
        return <PlanCard key={index} plan={plan}/>
      })}
    </div>
  )
  
}
