import React from 'react'

export default function PlanCard(props) {

  const plan = props.plan;


  return (
    <div>
      <div>
        {plan.title}
      </div>
      <div>
        {plan.content}
      </div>
      <div>
        {plan.start}
      </div>
      <div>
        {plan.end}
      </div>
      <br />
    </div>
  )
}
