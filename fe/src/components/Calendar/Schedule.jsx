import { motion } from "framer-motion";
import React from "react";
import { useRecoilValue, useSetRecoilState } from "recoil";
import styled from "styled-components";
import { blocksState, modalState } from "../atoms";

const DAYS_OF_WEEK = ["일", "월", "화", "수", "목", "금", "토"];
function Calendar({ weeks }) {
  const blocks = useRecoilValue(blocksState);
  const setModalOpen = useSetRecoilState(modalState);

  const onDatePress = (id) => {
    if (!id) return;
    setModalOpen({ state: true, id });
  };
  return (
    <Month>
      <Week style={{ borderBottom: "solid 2px white" }}>
        {DAYS_OF_WEEK.map((day, idx) => (
          <DateBox
            key={idx}
            height={25}
            style={{
              color: idx === 0 ? "#eb4034" : null,
              paddingBottom: "8px",
            }}
          >
            {day}
          </DateBox>
        ))}
      </Week>
      {weeks &&
        weeks.map((week, week_idx) => (
          <Week key={week_idx}>
            {!(week[0].value === "" && week_idx === 5) &&
              week.map((date, day_idx) => (
                <DateBox key={day_idx} onClick={() => onDatePress(date.id)}>
                  <span
                    style={{
                      position: "relative",
                      color: day_idx === 0 ? "#eb4034" : null,
                    }}
                  >
                    {date.value}
                  </span>
                  <ScrollBox>
                    {blocks[date.id] &&
                      blocks[date.id].map((data) => (
                        <Block done={data.done} key={data.id}>
                          {data.content}
                        </Block>
                      ))}
                  </ScrollBox>
                </DateBox>
              ))}
          </Week>
        ))}
    </Month>
  );
}

export default Calendar;

const Month = styled.div`
  display: flex;
  flex-direction: column;
  height: fit-content;
  border-radius: 10px;
  border: solid 10px whitesmoke;
  overflow: hidden;
  cursor: pointer;
`;
const Week = styled.div`
  display: grid;
  grid-template-columns: repeat(7, 1fr);
`;
const DateBox = styled(motion.div)`
  width: 10vw;
  height: ${(props) => (props.height ? props.height : "13vh")};
  align-items: center;
  background-color: whitesmoke;
  padding: 2px;
`;
export const Block = styled.div`
  background-color: ${(p) => (p.done ? "#bad5ff" : "#ffcd82")};
  border-radius: 5px;
  overflow: hidden;
  margin-top: 2px;
  padding: 1px;
  padding-bottom: 2px;
  width: 8vw;
  height: 1rem;
  justify-content: center;
  display: flex;
  font-size: 0.8rem;
`;
const ScrollBox = styled.div`
  -ms-overflow-style: none;
  height: 10vh;
  overflow-y: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;
