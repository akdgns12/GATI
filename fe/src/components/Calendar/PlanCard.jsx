import React from 'react'
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';

import { Grid } from '@mui/material';

export default function PlanCard(props) {

  const plan = props.plan;

  return (
      <Grid container direction='column'>
        <Grid item xs={12}>
          <Card value= 'sivla' sx={{ justifyContent: 'center', m: 2, backgroundColor: '#685DD833'}} variant="outlined">
            <CardContent>
              <Typography sx={{ fontSize: 25 }} color="text.secondary" gutterBottom>
                {plan.title}
              </Typography>
              
              <Typography variant="body2">
                {plan.start}
              </Typography>

              <Typography variant="body2">
                {plan.end}
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      </Grid>
  )
}
